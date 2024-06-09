<template>
    <svg-icon type="mdi" :path="path"></svg-icon>
  </template>
  
  <script>
  import SvgIcon from '@jamescoyle/vue-icon';
  import { mdiLogin } from '@mdi/js';
  
  export default {
    name: "icon-login",
    components: {
      SvgIcon
    },
    data() {
      return {
         path: mdiLogin,
      }
    }
  }
  </script>