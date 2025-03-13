/**
 * This exposes the native CalendarModule module as a JS module. This has a
 * function 'createCalendarEvent' which takes the following parameters:
 *
 * 1. String name: A string representing the name of the event
 * 2. String location: A string representing the location of the event
 */
import {NativeModules} from 'react-native';
const {CalendarModule} = NativeModules;

interface CalendarInterface {
  createCalendarEvent(name: string, location: string): void;
}
export default CalendarModule as CalendarInterface;


// Convert kiểu dữ liệu từ obj C sang javascript 

// NSString  -->	string, ?string
// BOOL   -->	boolean
// double	-->  number
// NSNumber	 -->   ?number
// NSArray   --> 	Array, ?Array
// NSDictionary --> 	Object, ?Object
// RCTResponseSenderBlock   -->	Function (success)
// RCTResponseSenderBlock, RCTResponseErrorBlock   --> 	Function (failure)
// RCTPromiseResolveBlock, RCTPromiseRejectBlock   -->	Promise