package com.rn_native.sakura.felica.ui;

import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class NfcReader {

    private static final String TAG = "NFC";

    public byte[][] readTag(Tag tag) {
        NfcF nfc = NfcF.get(tag);
        try {
            nfc.connect();
            // System code của System 1-> 0xFE00
            byte[] targetSystemCode = new byte[] { (byte) 0xfe, (byte) 0x00 };

            // Tạo command polling
            byte[] polling = polling(targetSystemCode);

            // Gửi command rồi lấy kết quả
            byte[] pollingRes = nfc.transceive(polling);

            // Lấy IDm của System 0 (Byte thứ 1 là data size, byte thứ 2 là response code,
            // size của IDm là 8 byte)
            byte[] targetIDm = Arrays.copyOfRange(pollingRes, 2, 10);

            // Size của data chứa trong service (Lần này là 4)
            int size = 4;

            // Service code của đối tượng là -> 0x1A8B
            byte[] targetServiceCode = new byte[] { (byte) 0x1A, (byte) 0x8B };

            // Tạo command Read Without Encryption
            byte[] req = readWithoutEncryption(targetIDm, size, targetServiceCode);

            // Gửi command rồi lấy kết quả
            byte[] res = nfc.transceive(req);
            Log.e(TAG, "res" + res);
            nfc.close();

            // Parse kết quả, chỉ lấy data
            return parse(res);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return null;
    }

    /**
     * Lấy command Pollin
     * System code chỉ định @param systemCode byte[]
     * Command @return Polling
     * 
     * @throws IOException
     */
    private byte[] polling(byte[] systemCode) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(100);

        bout.write(0x00); // Dummy của byte độ dài data
        bout.write(0x00); // Command code
        bout.write(systemCode[0]); // systemCode
        bout.write(systemCode[1]); // systemCode
        bout.write(0x01); // Request code
        bout.write(0x0f); // Timeslot

        byte[] msg = bout.toByteArray();
        msg[0] = (byte) msg.length; // 1 byte ở đầu là chiều dài data
        return msg;
    }

    /**
     * Lấy command Read Without Encryption
     * ID của system chỉ định @param IDm
     * Số data lấy @param size
     * Command @return Read Without Encryption
     * 
     * @throws IOException
     */
    private byte[] readWithoutEncryption(byte[] idm, int size, byte[] serviceCode) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(100);

        bout.write(0); // データ長バイトのダミー
        bout.write(0x06); // コマンドコード
        bout.write(idm); // IDm 8byte
        bout.write(1); // サービス数の長さ(以下２バイトがこの数分繰り返す)

        // Việc chỉ định service code là little endian nên sẽ chỉ định từ low byte.
        bout.write(serviceCode[1]); // Service code low byte
        bout.write(serviceCode[0]); // Service code high byte
        bout.write(size); // Số block

        // Chỉ định Block number
        for (int i = 0; i < size; i++) {
            bout.write(0x80); // Block element high byte
            bout.write(i); // Block number
        }

        byte[] msg = bout.toByteArray();
        msg[0] = (byte) msg.length; // 1 byte ở đầu là chiều dài của data
        return msg;
    }

    /**
     * Phân tích kết quả Read Without Encryption
     * 
     * @param res byte[]
     *            Show string @return
     * @throws Exception
     */
    private byte[][] parse(byte[] res) throws Exception {
        // res[10] error code. Trường hợp 0x00 thì không có vấn đề gì
        if (res[10] != 0x00)
            throw new RuntimeException("Read Without Encryption Command Error");

        // res[12] Số block trả lời
        // res[13 + n * 16] Lặp lại 16 data thực (byte/block)
        int size = res[12];
        byte[][] data = new byte[size][16];
        String str = "";
        for (int i = 0; i < size; i++) {
            byte[] tmp = new byte[16];
            int offset = 13 + i * 16;
            for (int j = 0; j < 16; j++) {
                tmp[j] = res[offset + j];
            }

            data[i] = tmp;
        }
        Log.e(TAG, "data" + data);
        return data;
    }
}
