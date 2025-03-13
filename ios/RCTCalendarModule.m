// RCTCalendarModule.m
#import "RCTCalendarModule.h"
#import <React/RCTLog.h>

@implementation RCTCalendarModule {
  bool hasListeners;
}
   // Đây chính là tên của module

// To export a module named RCTCalendarModule
RCT_EXPORT_MODULE();  // Đây chính là câu lệnh export ra 1 module để sử dụng ở phía react native

// To export a module named CalendarModuleFoo
//RCT_EXPORT_MODULE(CalendarModuleFoo);  // Nếu mà biết thay đổi tên ở đây thì bên JS sẽ dùng tên đó
//const {CalendarModuleFoo} = ReactNative.NativeModules;

// Nếu bạn không chỉ định tên, tên mô-đun JavaScript sẽ khớp với tên lớp Objective-C, với mọi tiền tố "RCT" hoặc "RK" sẽ bị xóa.

//RCT_EXPORT_METHOD(createCalendarEvent:(NSString *)name location:(NSString *)location)
//{
//}

// RCT_EXPORT_METHOD : chính là tạo ra 1 methoc để bên React Native có thể sử dụng , có 2 tham số là name và location


RCT_EXPORT_METHOD(createCalendarEvent:(NSString *)name location:(NSString *)location)
{
 RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);   // Hàm in log ở bên phía native ios
}

RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(getName)
{
return [[UIDevice currentDevice] name];
}

// Xuất hằng số từ bên ios sang cho JS
- (NSDictionary *)constantsToExport
{
 return @{ @"DEFAULT_EVENT_NAME": @"New Event" };
}


// Tạo 1 method mà có cả callback ở bên phía JS
RCT_EXPORT_METHOD(createCalendarEventHaveCallback:(NSString *)title location:(NSString *)location callback: (RCTResponseSenderBlock)callback)
{
  NSInteger eventId = 100;
 callback(@[@(eventId)]);   // Tham số eventId được truyền sang cho js

 RCTLogInfo(@"Pretending to create an event %@ at %@", title, location);
}


// Xử lý lỗi , tham số được truyền bên ios , còn logic được sử lý bên js dựa theo tham số từ ios đưa sang

RCT_EXPORT_METHOD(createCalendarEventCallback:(NSString *)title location:(NSString *)location callback: (RCTResponseSenderBlock)callback)
{
  NSNumber *eventId = [NSNumber numberWithInt:123];
  callback(@[[NSNull null], eventId]);
}

// Lựa chọn sẽ chạy hàm callback nào logic được viết bên phía ios - bên js truyền 2 callback xuống ios
RCT_EXPORT_METHOD(createCalendarEventCallbackLogicSideIOS:(NSString *)title
                  location:(NSString *)location
                  errorCallback: (RCTResponseSenderBlock)errorCallback
                  successCallback: (RCTResponseSenderBlock)successCallback)
{
  @try {
    NSNumber *eventId = [NSNumber numberWithInt:123];
    successCallback(@[eventId]);
  }

  @catch ( NSException *e ) {
    errorCallback(@[e]);
  }
}


// Trong ios cũng có thể bắn ra 1 promise và bên phía js sẽ có thể hứng được

//// Khai báo hàm C trước khi gọi nó
//NSInteger createCalendarEventPromise(void);  // Cái này không đúng
//
//RCT_EXPORT_METHOD(createCalendarEventPromise:(NSString *)title
//                 location:(NSString *)location
//                 resolver:(RCTPromiseResolveBlock)resolve
//                 rejecter:(RCTPromiseRejectBlock)reject)
//{
// NSInteger eventId = createCalendarEventPromise();  // Đang bị lỗi chưa khai báo hàm
// if (eventId) {
//    resolve(@(eventId));
//  } else {
//    reject(@"event_failure", @"no event id returned", nil);
//  }
//}


//// Để có thể sendEvent từ phía bên IOS
//// Will be called when this module's first listener is added.
//-(void)startObserving {
//    hasListeners = YES;
//    // Set up any upstream listeners or background tasks as necessary
//}
//
//// Will be called when this module's last listener is removed, or on dealloc.
//-(void)stopObserving {
//    hasListeners = NO;
//    // Remove upstream listeners, stop unnecessary background tasks
//}
//
//- (void)calendarEventReminderReceived:(NSNotification *)notification
//{
//  NSString *eventName = notification.userInfo[@"name"];
//  if (hasListeners) {// Only send events if anyone is listening
//    [self sendEventWithName:@"EventReminder" body:@{@"name": eventName}];
//  }
  
@end
