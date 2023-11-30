import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import {MyView} from './MyView';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App_AndroidFragment extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {
      progress: -1,
    };
  }

  render() {
    return (
      <View style={(styles.container, {backgroundColor: 'red'})}>
        <Text>{instructions}</Text>
        <Text style={styles.welcome} onPress={this.onClick}>
          Click here {this.state.progress}
        </Text>
        <View
          style={{
            margin: 20,
            // height: 300,
            // width: 300,
            backgroundColor: 'pink',
          }}>
          <MyView style={{margin: 20}} />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    // backgroundColor: '#F5FCFF',
    backgroundColor: 'red',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
    backgroundColor: '#f00',
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
