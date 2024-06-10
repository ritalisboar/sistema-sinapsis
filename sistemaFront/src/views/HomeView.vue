<template>
  <div class="about">
    <h1>Subestações</h1>
    <SubstationTable
      :substations="substations"
      @delete="handleDelete"
      @edit="handleEdit"
      @show-map="handleShowMap"
    />
    <br/>
    <BaseButton 
    @click="handleInclude"
    width="100px"
    height="40px"
  >
    Incluir
  </BaseButton>
  <ModalAlert :showChoiceButton="true" :visible="modalVisible" :message="modalMessage" @close="closeModal" @accept="handleDeleteConfirm" />
  <ModalAlert :visible="modalSuccessVisible" :message="modalSuccessMessage" @close="closeModalSuccess" />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import SubstationTable from '../components/table/SubstationTable.vue';
import BaseButton from '../components/button/Button.vue'
import ModalAlert from '../components/modal/ModalAlert.vue'
import { useRouter } from 'vue-router'
import { tagging } from '@/utils/Utils';
import axios from 'axios'

type handleShowMapData = {
  code: string
  name: string
}

type substationType = {
  codigo: string
  nome: string
  id_subestacao: number
  latitude: number
  longitude: number
  redeMT: [{
    codigo: string
    nome: string
  }]
}

export default defineComponent({
  name: 'HomeView',
  components: {
    SubstationTable,
    BaseButton,
    ModalAlert,
  },
  setup() {
    const router = useRouter()
    const substations = ref<substationType[]>([])
    const modalVisible = ref(false);
    const modalMessage = ref('Deseja realmente excluir o item?');
    const modalSuccessVisible = ref(false);
    const modalSuccessMessage = ref('');

    const deleteItem = ref('')

    const fetchSubstationList = async () => {
      axios.get('http://localhost:8080/substation/list').then(response => {
        substations.value = response.data
      }).catch(err => {
        console.log('err', err)
      })
    };

    const deleteSubstation = async (code: string, id_substacao: number) => {
      console.log('DELETE ID SUBSTACAO', id_substacao)
      axios.delete(`http://localhost:8080/substation/${id_substacao}`).then(response => {
        if (response.status === 200) {
          modalVisible.value = false
          substations.value = substations.value.filter(sub => sub.codigo !== code);
          modalSuccessVisible.value = true
          modalSuccessMessage.value = response.data
        } 
      }).catch(err => {
        console.log('err', err)
      })
    };
  
    onMounted(() => {
      fetchSubstationList();
      tagging("HomeView", 'showHomeView')
    })

    const handleDelete = (code: string) => {
      tagging("HomeView", 'handleDelete')
      modalVisible.value = true
      deleteItem.value = code
    };
    
    const handleDeleteConfirm = () => {
      tagging("HomeView", 'handleDeleteConfirm')
      console.log('DELETE -> ', deleteItem.value)
      const id_subestacao = substations.value.find(item => item.codigo === deleteItem.value).id_subestacao
      deleteSubstation(deleteItem.value, id_subestacao)
    };

    const handleEdit = (data: handleShowMapData) => {
      tagging("HomeView", 'handleEdit')
      const sub = substations.value.find(item => item.codigo === data.code)
      const latitude = sub?.latitude
      const longitude = sub?.longitude
      router.push({ name: 'alter', params: { code: data.code, name: data.name, latitude, longitude } })
    };

    const handleShowMap = (data: handleShowMapData) => {
      tagging("HomeView", 'handleShowMap')
      const sub = substations.value.find(item => item.codigo === data.code)
      const latitude = sub?.latitude
      const longitude = sub?.longitude
      router.push({ name: 'preview', params: { code: data.code, name: data.name, latitude, longitude } })
    };

    const handleInclude = () => {
      tagging("HomeView", 'handleInclude')
      router.push({ name: 'include' })
    };

    const closeModal = () => {
      tagging('HomeView', 'closeModal')
      modalVisible.value = false;
    };

    const closeModalSuccess = () => {
      tagging('HomeView', 'closeModalSuccess')
      modalSuccessVisible.value = false;
    };

    return {
      substations,
      handleDelete,
      handleEdit,
      handleShowMap,
      handleInclude,
      modalVisible, 
      modalMessage,
      closeModal,
      handleDeleteConfirm,
      modalSuccessVisible,
      modalSuccessMessage,
      closeModalSuccess
    };
  },
});
</script>

<style>
@media (min-width: 1024px) {
  .about {
    padding: 20px;
    color: #ffffff;
    min-height: 100vh;

  }
}
</style>
