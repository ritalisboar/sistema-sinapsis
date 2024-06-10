<template>
<div class="add-substation">
    <h2>Subestação</h2>
    <form @submit.prevent="handleSubmit">
      <TextInput
        id="code-input"
        v-model:value="substationCode"
        label="Código:"
        :disabled="isDisabled"
      />
      <TextInput
        id="name-input"
        v-model:value="substationName"
        label="Nome:"
        :disabled="isDisabledAll"
      />
      <TextInput
        id="latitude-input"
        v-model:value="latitude"
        label="Latitude:"
        :disabled="isDisabledAll"
      />
      <TextInput
        id="longitude-input"
        v-model:value="longitude"
        label="Longitude:"
        :disabled="isDisabledAll"
      />
    </form>
  </div>
  <br />
  <div v-if="showRedeMT" class="add-rede-mt">
    <h2>Rede MT</h2>
    <form @submit.prevent="handleSubmit">
      <TextInput
        id="rede-code-input"
        v-model:value="redeCode"
        label="Código:"
      />
      <TextInput
        id="rede-name-input"
        v-model:value="redeName"
        label="Nome:"
      />
      <Button type="submit" width="200px" height="50px">
        Adicionar
      </Button>
    </form>
    
    <h3>Redes MT Adicionadas</h3>
    <table>
      <thead>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="rede in redesMT" :key="rede.redeCode">
          <td>{{ rede.redeCode ? rede.redeCode : rede.codigo }}</td>
          <td>{{ rede.redeName ? rede.redeName : rede.nome }}</td>
          <td>
            <button @click="deleteRedeMT(rede.redeCode)">Excluir</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, getCurrentInstance, onMounted } from 'vue';
import TextInput from '../textInput/TextInput.vue';
import Button from '../button/Button.vue';
import { useRoute } from 'vue-router'
import { tagging } from '@/utils/Utils';

export default defineComponent({
  name: 'AddSubstation',
  components: {
    TextInput,
    Button,
  },
  props: {
    showRedeMT: {
      type: Boolean,
      default: true
    },
    routeType: {
      type: String,
      default: ""
    },
    rede: {
      type: Array,
      default: () => []
    }
  },
  setup(props) {
    const { emit } = getCurrentInstance();
    const route = useRoute()

    const substationCode = ref('');
    const substationName = ref('');
    const latitude = ref('');
    const longitude = ref('');

    const redeCode = ref('');
    const redeName = ref('');
    const redesMT = ref([]);

    const isDisabled = ref(false)
    const isDisabledAll = ref(false)

    const substationIncluded = ref([])

    const validateSubstationParams = () => {
      tagging('AddSubstation', 'validateSubstationParams')
      if (route.params.code) {
        substationCode.value = route.params.code
        substationName.value = route.params.name
        isDisabled.value = true
      }
    }

    const validateCoordinatorParams = () => {
      tagging('AddSubstation', 'validateCoordinatorParams')
      if (route.params.latitude && route.params.longitude) {
        latitude.value = route.params.latitude
        longitude.value = route.params.longitude
      }
    }

    const validatePreviewRoute = () => {
      tagging('AddSubstation', 'validatePreviewRoute')
      if (route.name === 'preview') {
        isDisabledAll.value = true
      }
    }

    onMounted(() => {
      setTimeout(() => {
        redesMT.value = props.rede.map(item => item)
      }, 1000)
      tagging('AddSubstation', 'AddSubstationPreview')
      validateSubstationParams()
      validateCoordinatorParams()
      validatePreviewRoute()
    })

    const handleSubmit = () => {
      tagging('AddSubstation', 'handleSubmit')
      const newSubstation = {
        substationCode: substationCode.value,
        substationName: substationName.value,
        latitude: latitude.value,
        longitude: longitude.value,
      };
    
      if (!substationIncluded.value.find(item => item.substationCode)){
        substationIncluded.value.push(newSubstation)
      }

      const newRedeMT = {
        redeCode: redeCode.value,
        redeName: redeName.value,
      };
      redesMT.value.push(newRedeMT);
      substationIncluded.value.push(redesMT);
      
      emit('add-rede-mt', substationIncluded.value);

      redeCode.value = '';
      redeName.value = '';
    };


    const deleteRedeMT = (code: string) => {
      tagging('AddSubstation', 'deleteRedeMT')
      redesMT.value = redesMT.value.filter(rede => rede.redeCode !== code);
      substationIncluded.value = substationIncluded.value.filter(rede => rede.redeCode !== code);
    };

    return {
      substationCode,
      substationName,
      latitude,
      longitude,
      redeCode,
      redeName,
      redesMT,
      handleSubmit,
      deleteRedeMT,
      isDisabled,
      isDisabledAll,
      validateSubstationParams,
      validateCoordinatorParams,
      validatePreviewRoute,
    };
  },
});
</script>

<style scoped>
.add-substation {
  padding: 20px;
  background-color: #121212;
  color: #ffffff;
  border: 1px solid #444444;
  border-radius: 5px;
}

.add-substation h2 {
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

button {
  align-self: flex-start;
}
</style>

<style scoped>
.add-rede-mt {
  padding: 20px;
  background-color: #121212;
  color: #ffffff;
  border: 1px solid #444444;
  border-radius: 5px;
}

.add-rede-mt h2 {
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

button {
  background-color: #444444;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  border-radius: 3px;
}

button:hover {
  background-color: #555555;
}

table {
  width: 100%;
  margin-top: 20px;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #444444;
  color: white;
  text-align: left;
}

td {
  background-color: #333333;
  color: white;
}
</style>
