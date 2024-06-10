<template>
  <div class="alter">
    <h1>Alteração</h1>
    <br/>
    <AddSubstation 
      @add-rede-mt="addRedeMT" 
      :showRedeMT="true"
      :rede="redeMtCurrent"
    />
    <br/>
    <div>
      <BaseButton 
        type="submit" 
        width="200px" 
        height="50px" 
        @click="addSubstationAndRedeMT"
      >
        Efetivar Alterações
      </BaseButton>
    </div>
    <ModalAlert :visible="modalVisible" :message="modalMessage" @close="closeModal" />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import AddSubstation from '../components/substation/AddSubstation.vue'
import BaseButton from '../components/button/Button.vue'
import ModalAlert from '../components/modal/ModalAlert.vue'
import { useRouter, useRoute } from 'vue-router'
import { tagging } from '@/utils/Utils';
import axios from 'axios'

export default defineComponent({
  name: 'AlterView',
  components: {
    AddSubstation,
    BaseButton,
    ModalAlert,
  },
  props: {
    code: {
      type: String,
      default: '',
    },
  },
  setup(props) {
    const router = useRouter()
    const route = useRoute()
    const substationCurrent = ref([]);

    const modalVisible = ref(false);
    const modalMessage = ref('');

    const redeMtCurrent = ref([]);
    
    const fetchRedeMtList = async () => {
      axios.get('http://localhost:8080/substation/list').then(response => {
        const sub = response.data.find(substation => substation.codigo === route.params.code)
        redeMtCurrent.value = sub.redesMT
        console.log('ndjsndjs', sub.redesMT)
      }).catch(err => {
        console.log('err', err)
      })
    };

    onMounted(() => {
      fetchRedeMtList()
      tagging('AlterView', 'showAlterView')
    })

    const addRedeMT = (newRedeMT) => {
      tagging('AlterView', 'addRedeMT')
      substationCurrent.value = newRedeMT;
    };

    const addSubstationAndRedeMT = () => {
      tagging('AlterView', 'addSubstationAndRedeMT')
      modalVisible.value = true;
      modalMessage.value = 'Subestação e Rede MT alteradas com sucesso!';
    };

    const closeModal = () => {
      tagging('AlterView', 'closeModal')
      router.back()
      modalVisible.value = false;
    };

    return {
      addRedeMT,
      addSubstationAndRedeMT,
      modalVisible,
      modalMessage,
      closeModal,
      fetchRedeMtList,
      redeMtCurrent
    };
  },
});
</script>

<style>
@media (min-width: 1024px) {
  .alter {
    padding: 20px;
    color: #ffffff;
    min-height: 100vh;
  }
}
</style>
