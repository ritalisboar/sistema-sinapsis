<template>
  <div class="modal" v-if="visible">
    <div class="modal-content">
      <span class="close" @click="close">&times;</span>
      <p>{{ message }}</p>
      <button v-if="showChoiceButton" @click="accept">Sim</button>
      <button @click="close">Fechar</button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'ModalAlert',
  props: {
    visible: {
      type: Boolean,
      required: true,
    },
    message: {
      type: String,
      required: true,
    },
    showChoiceButton: {
      type: Boolean,
      default: false
    },
  },
  methods: {
    close() {
      this.$emit('close');
    },
    accept() {
      this.$emit('accept');
    },
  },
};
</script>

<style scoped>
.modal {
  position: fixed; 
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%; 
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.4); 
}

.modal-content {
  background-color: #333; 
  color: #fff; 
  border-radius: 8px; 
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  width: 80%; 
  max-width: 500px; 
  text-align: center; 
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #fff;
  text-decoration: none;
  cursor: pointer;
}

button {
  background-color: #444; 
  color: #fff; 
  border: none;
  padding: 10px 20px;
  margin-top: 20px;
  margin-right: 20px;
  cursor: pointer;
  border-radius: 4px;
}

button:hover {
  background-color: #555; 
}
</style>
