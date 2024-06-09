<template>
  <button
    class="dark-button"
    @click="handleClick"
    :style="{ width, height }"
  >
    <slot></slot>
  </button>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';

export default defineComponent({
  name: 'DarkButton',
  props: {
    width: {
      type: String as PropType<string>,
      required: false,
      default: 'auto',
    },
    height: {
      type: String as PropType<string>,
      required: false,
      default: 'auto',
    },
  },
  emits: ['click'],
  methods: {
    handleClick(event: MouseEvent) {
      this.$emit('click', event);
    },
  },
});
</script>

<style scoped>
.dark-button {
  background-color: #444444;
  color: #ffffff;
  border: 1px solid #555555;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, border-color 0.3s;
}

.dark-button:hover {
  background-color: #555555;
  border-color: #666666;
}

.dark-button:focus {
  outline: none;
  border-color: #777777;
}

.dark-button:active {
  background-color: #333333;
}
</style>
