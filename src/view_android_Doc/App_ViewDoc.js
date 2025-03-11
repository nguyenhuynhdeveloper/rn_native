import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, TouchableOpacity} from 'react-native';
// import CircularProgressButton from './src/CircularProgressButton';
import ImageView from './ImageView';
// import Image frm '../assets/hot-girl.jpg'

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App_ViewDoc extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {
      progress: -1,
    };
  }

  render() {
    return (
      <View style={(styles.container, {backgroundColor: 'red'})}>
        {/* <Text>{instructions}</Text> */}
        <Text style={styles.welcome} onPress={this.onClick}>
          Click here {this.state.progress}
        </Text>
        <ImageView
          // src={
          //   'https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_92x30dp.png'
          // }

          src={[
            'https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_92x30dp.png',
          ]}

          // src={["../assets/hot-girl.jpg"]}
          // src={['@drawable.ic_laucher']}
          // src={["R.drawable.ic_laucher"]}
          // style={{ height: 100, width: 100 }}
        />
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
