<template>
  <l-map ref="map" v-model:zoom="zoom" :center="center">
    <l-tile-layer
      url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      layer-type="base"
      name="OpenStreetMap"
    ></l-tile-layer>
  </l-map>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
import { tagging } from "@/utils/Utils";
import { LMap, LTileLayer } from "@vue-leaflet/vue-leaflet";
import { onMounted } from 'vue';
import { useRoute } from 'vue-router'
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';


export default defineComponent({
  name: 'MapsView',
  components: {
    LMap,
    LTileLayer,
  },
  data() {
    const route = useRoute()
    let coordinates: Number[] = []

    const validateParams = () => {
      tagging('Maps', 'validateParams')
      if (route.params.latitude && route.params.longitude) {
        coordinates.push(Number(route.params.latitude), Number(route.params.longitude))
      }
    }
      
    onMounted(() => {
      tagging('Maps', 'showMaps')
      validateParams()
    })

    return {
      zoom: 17,
      center: coordinates,
      validateParams,
    };
  },
});
</script>

<style></style>