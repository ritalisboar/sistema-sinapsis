<template>
  <div class="include">
    <h1>Incluir</h1>
    <br/>
    <AddSubstation @add-rede-mt="addRedeMT" @add-substation="addSubstationCurrent" :showRedeMT="true" />
    <br/>
    <div>
      <BaseButton 
        type="submit" 
        width="200px" 
        height="50px" 
        @click="addSubstationAndRedeMT"
      >
        Incluir
      </BaseButton>
    </div>
    <ModalAlert :visible="modalVisible" :message="modalMessage" @close="closeModal" />
    <ModalAlert :visible="modalErrorVisible" :message="modalErrorMessage" @close="closeModalError" />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import AddSubstation from '../components/substation/AddSubstation.vue'
import BaseButton from '../components/button/Button.vue'
import ModalAlert from '../components/modal/ModalAlert.vue'
import { useRouter } from 'vue-router'
import { tagging } from '@/utils/Utils';
import axios from 'axios'

type SubstationRequestBody = {
  substationCode: string
  substationName: string
  latitude: number
  longitude: number
  redesMT: []
}

export default defineComponent({
  name: 'IncludeView',
  components: {
    AddSubstation,
    BaseButton,
    ModalAlert,
  },
  setup() {
    const router = useRouter()
    const redesMT = ref([]);
    const substationInserted = ref([]);

    const modalVisible = ref(false);
    const modalMessage = ref('');
    const modalErrorVisible = ref(false);
    const modalErrorMessage = ref('');

    onMounted(() => {
      tagging('IncludeView', 'showIncludeView')
    })

    const addRedeMT = (newRedeMT) => {
      tagging('IncludeView', 'addRedeMT')
      redesMT.value = newRedeMT;
    };

    const addSubstationCurrent = (newSubstation) => {
      tagging('IncludeView', 'addSubstationCurrent')
      substationInserted.value = newSubstation;
    };

    const includeSubstation = async (requestBody: SubstationRequestBody) => {
      console.log('SUBS', requestBody)
      if (requestBody && requestBody.substationCode !== '') {
        const bodyFormatter = {
          codigo: requestBody.substationCode,
          nome: requestBody.substationName,
          latitude: Number(requestBody.latitude),
          longitude: Number(requestBody.longitude),
        }
        
        try {
          const response = await axios.post('http://localhost:8080/substation/', bodyFormatter);
          console.log('AXIOS RESPONSE', response)
        }catch (error) {
          modalErrorVisible.value = true;
          modalErrorMessage.value = 'Ocorreu um erro, tente novamente. Verifique os campos de Latitude e Longitude.';
          console.log('err', error)
        }
      } else {
        modalErrorVisible.value = true;
        modalErrorMessage.value = 'As informações da subestação são obrigatórias. Por favor, preencha-as e aperte em "Adicionar"';
      }
    };

    const addSubstationAndRedeMT = () => {
      tagging('IncludeView', 'addSubstationAndRedeMT')

      includeSubstation(substationInserted.value[0])
    };

    const closeModal = () => {
      tagging('IncludeView', 'closeModal')
      router.back()
      modalVisible.value = false;
    };

    const closeModalError = () => {
      tagging('IncludeView', 'closeModalError')
      modalErrorVisible.value = false;
    };

    return {
      addRedeMT,
      addSubstationCurrent,
      addSubstationAndRedeMT,
      modalVisible,
      modalMessage,
      closeModal,
      modalErrorMessage,
      modalErrorVisible,
      closeModalError,
    };
  },
});
</script>

<style>
@media (min-width: 1024px) {
  .include {
    padding: 20px;
    color: #ffffff;
    min-height: 100vh;
  }
}
</style>
