package com.rn_native.sakura.felica.felicahistory

import android.util.Log

/**
 * ICOCA,PiTaPa履歴コード
 * 参考：https://osdn.jp/projects/felicalib/wiki/suica
 */
class IcocaPitapaHistory {

    private val TAG = "NFC_IcocaPitapaHistory"

  var remain: Int = 0

  private fun init(res: ByteArray, off: Int) {
    this.remain = toInt(res, off, 11, 10) //11-10: ICOCA,PiTaPa残高
    Log.d(TAG, "res:" + res)
  }

  private fun toInt(res: ByteArray, off: Int, vararg idx: Int): Int {
    var num = 0
    for (i in idx.indices) {
      num = num shl 8
      num += res[off + idx[i]].toInt() and 0x0ff
      Log.d(TAG, "toInt:" + num)
    }

    return num
  }

  override fun toString(): String = "残高：" + remain + "円"

  companion object {

    fun parse(res: ByteArray, off: Int): IcocaPitapaHistory {
      val self = IcocaPitapaHistory()
      self.init(res, off)
      return self
    }
  }
}
