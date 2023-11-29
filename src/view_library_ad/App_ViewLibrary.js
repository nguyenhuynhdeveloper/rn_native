import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, TouchableOpacity} from 'react-native';
// import CircularProgressButton from './src/CircularProgressButton';
import CircularProgressButton from './CircularProgressButton';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App_ViewLibrary extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {
      progress: -1,
    };
  }

  onClick = () => {
    if (this.state.progress == -1) {
      this.setState({
        progress: 0,
      });
    } else if (this.state.progress == 0) {
      this.setState({
        progress: 100,
      });
    } else if (this.state.progress == 100) {
      this.setState({
        progress: 0,
      });
    }
  };

  render() {
    return (
      <View style={styles.container}>
        <Text>{instructions}</Text>
        <Text style={styles.welcome} onPress={this.onClick}>
          Click here {this.state.progress}
        </Text>
        <TouchableOpacity onPress={this.onClick}>
          <CircularProgressButton
            style={{height: 100, width: 100}}
            text="text"
            completeText="Done"
            errorText="Error"
            idleText="idleText"
            progress={this.state.progress}
          />
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
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
