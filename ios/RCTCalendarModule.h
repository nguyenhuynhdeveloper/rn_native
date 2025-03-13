//  RCTCalendarModule.h
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>  // Thêm dòng này để có thể sendEventWithName


@interface CalendarModule : RCTEventEmitter <RCTBridgeModule>  // Cập nhật dòng này để có thể sendEventWithName

@end
@interface RCTCalendarModule : NSObject <RCTBridgeModule>
// RCTCalendarModule : Đây chính là khai báo tên của module sẽ được sử dụng bên phía React Native




//Các mô-đun gốc có thể báo hiệu các sự kiện cho JavaScript mà không cần phải gọi trực tiếp. Ví dụ, bạn có thể muốn báo hiệu cho JavaScript lời nhắc rằng sự kiện lịch từ ứng dụng lịch iOS gốc sẽ sớm xảy ra. Cách tốt nhất để thực hiện việc này là phân lớp RCTEventEmitter, triển khai supportedEventsvà gọi self sendEventWithName:


@end
