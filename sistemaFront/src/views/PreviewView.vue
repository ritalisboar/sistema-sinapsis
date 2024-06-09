<template>
  <div class="preview">
    <h1>Visualização</h1>
    <br/>
    <AddSubstation 
      :showRedeMT="false" 
    />
    <br/>
    <Maps  />
    <br/>
    <div>
      <BaseButton 
        type="submit" 
        width="200px" 
        height="50px" 
        @click="goBack"
      >
        Voltar
      </BaseButton>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from 'vue';
import AddSubstation from '../components/substation/AddSubstation.vue'
import BaseButton from '../components/button/Button.vue'
import { useRouter } from 'vue-router';
import "leaflet/dist/leaflet.css";
import Maps from '../components/maps/Maps.vue'
import { tagging } from '@/utils/Utils';

export default defineComponent({
  name: 'PreviewView',
  components: {
    AddSubstation,
    BaseButton,
    Maps,
  },
  setup() {
    const router = useRouter()

    onMounted(() => {
      tagging('PreviewView', 'showPreviewView')
    })

    const goBack = () => {
      tagging('PreviewView', 'goBack')
      router.go(-1)
    };

    return {
      goBack,
    };
  },
});
</script>

<style>
@media (min-width: 1024px) {
  .preview {
    padding: 20px;
    color: #ffffff;
    min-height: 100vh;
  }
}
</style>
