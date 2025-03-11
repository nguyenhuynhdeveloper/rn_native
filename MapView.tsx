import React from 'react';

import { requireNativeComponent } from 'react-native';

const RNTMap = requireNativeComponent('RNTMap');

type RegionChangeEvent = {
      nativeEvent: {
            latitude: number;
            longitude: number;
            latitudeDelta: number;
            longitudeDelta: number;
      };
};

export default function MapView(props: {
      /**
       * The region to be displayed by the map.
       *
       * The region is defined by the center coordinates and the span of
       * coordinates to display.
       */
      region?: {
            /**
             * Coordinates for the center of the map.
             */
            latitude: number;
            longitude: number;

            /**
             * Distance between the minimum and the maximum latitude/longitude
             * to be displayed.
             */
            latitudeDelta: number;
            longitudeDelta: number;
      };

      /**
  * Callback that is called continuously when the user is dragging the map.
  */
      onRegionChange: (event: RegionChangeEvent) => unknown;

      /**
       * Whether the user may use pinch gestures to zoom in and out.
       */
      zoomEnabled?: boolean;
}) {
      return <RNTMap {...props} onRegionChange={props.onRegionChange} />;
}