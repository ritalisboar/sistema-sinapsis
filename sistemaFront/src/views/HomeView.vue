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

type handleShowMapData = {
  code: string
  name: string
}

export default defineComponent({
  name: 'HomeView',
  components: {
    SubstationTable,
    BaseButton,
  },
  setup() {
    const router = useRouter()
    const substations = ref([
      { code: '001', name: 'Subestação 1' },
      { code: '002', name: 'Subestação 2' },
      { code: '003', name: 'Subestação 3' },
    ]);

    onMounted(() => {
      tagging("HomeView", 'showHomeView')
    })

    const handleDelete = (code: string) => {
      tagging("HomeView", 'handleDelete')
      substations.value = substations.value.filter(sub => sub.code !== code);
    };

    const handleEdit = (code: string) => {
      tagging("HomeView", 'handleEdit')
      router.push({ name: 'alter', params: { code } })
    };

    const handleShowMap = (data: handleShowMapData) => {
      tagging("HomeView", 'handleShowMap')
      const latitude = '-11.0060078'
      const longitude = '-37.1013934'
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
