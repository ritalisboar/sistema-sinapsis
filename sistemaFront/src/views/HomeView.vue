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
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import SubstationTable from '../components/table/SubstationTable.vue';
import BaseButton from '../components/button/Button.vue'
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
  },
  setup() {
    const router = useRouter()
    const substations = ref<substationType[]>([])

    const fetchSubstationList = async () => {
      axios.get('http://localhost:8080/substation/list').then(response => {
        console.log('AXIOS RESPONSE', response)
        substations.value = response.data
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
      substations.value = substations.value.filter(sub => sub.codigo !== code);
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

    return {
      substations,
      handleDelete,
      handleEdit,
      handleShowMap,
      handleInclude,
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
