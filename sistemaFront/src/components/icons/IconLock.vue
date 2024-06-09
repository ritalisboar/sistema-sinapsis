<template>
  <svg-icon type="mdi" :path="path"></svg-icon>
</template>

<script>
import SvgIcon from '@jamescoyle/vue-icon';
import { mdiLock } from '@mdi/js';

export default {
  name: "lock-icon",
  components: {
    SvgIcon
  },
  data() {
    return {
       path: mdiLock,
    }
  }
}
</script>