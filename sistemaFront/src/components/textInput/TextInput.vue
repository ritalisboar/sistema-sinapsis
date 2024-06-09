<template>
  <div class="dark-text-input">
    <label :for="id">{{ label }}</label>
    <input
      :type="type"
      :id="id"
      :value="value"
      :disabled="disabled"
      @input="onInput"
      class="dark-input"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';

export default defineComponent({
  name: 'DarkTextInput',
  props: {
    id: {
      type: String,
      required: true,
    },
    value: {
      type: String as PropType<string>,
      required: true,
    },
    label: {
      type: String as PropType<string>,
      required: false,
      default: '',
    },
    type: {
      type: String as PropType<string>,
      required: false,
      default: 'text',
    },
    disabled: {
      type: Boolean as PropType<boolean>,
      required: false,
      default: false,
    },
  },
  emits: ['update:value'],
  methods: {
    onInput(event: Event) {
      const target = event.target as HTMLInputElement;
      this.$emit('update:value', target.value);
    },
  },
});
</script>

<style scoped>
.dark-text-input {
  display: flex;
  flex-direction: column;
  color: #ffffff;
}

.dark-input {
  background-color: #333333;
  color: #ffffff;
  border: 1px solid #555555;
  padding: 10px;
  border-radius: 5px;
  margin-top: 5px;
}

.dark-input:focus {
  outline: none;
  border-color: #777777;
}
</style>
