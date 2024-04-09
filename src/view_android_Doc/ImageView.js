import React from 'react';
import { requireNativeComponent, View } from 'react-native';
import PropTypes from 'prop-types';

const ImageView = props => <RCTImageView {...props} />;

const RCTImageView = requireNativeComponent('RCTImageViewDoc');

RCTImageView.propTypes = {
  ...View.propTypes,
};

export default ImageView;

// ImageView.js

// var { requireNativeComponent } = require('react-native');
// import PropTypes from 'prop-types';
// var iface = {
//   name: 'ImageView',
//   propTypes: {
//     src: PropTypes.string,
//     borderRadius: PropTypes.number,
//     resizeMode: PropTypes.oneOf(['cover', 'contain', 'stretch']),
//   },
// };

// module.exports = requireNativeComponent('RCTImageViewDoc', iface);
