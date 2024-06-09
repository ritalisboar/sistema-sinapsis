<template>
  <svg-icon type="mdi" :path="path"></svg-icon>
</template>

<script>
import SvgIcon from '@jamescoyle/vue-icon';
import { mdiAccountCircle } from '@mdi/js';

export default {
  name: "account-circle",
  components: {
    SvgIcon
  },
  data() {
    return {
       path: mdiAccountCircle,
    }
  }
}
</script>