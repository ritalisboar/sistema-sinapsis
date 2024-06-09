<template>
  <div class="substation-table">
    <table>
      <thead>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Excluir</th>
          <th>Alterar</th>
          <th>Exibir no Mapa</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="substation in substations" :key="substation.code">
          <td>{{ substation.code }}</td>
          <td>{{ substation.name }}</td>
          <td>
            <button @click="deleteSubstation(substation.code)">Excluir</button>
          </td>
          <td>
            <button @click="editSubstation(substation.code)">Alterar</button>
          </td>
          <td>
            <button @click="showOnMap(substation.code, substation.name)">Exibir no Mapa</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'SubstationTable',
  props: {
    substations: {
      type: Array,
      required: true,
    },
  },
  emits: ['delete', 'edit', 'show-map'],
  methods: {
    deleteSubstation(code: string) {
      this.$emit('delete', code);
    },
    editSubstation(code: string) {
      this.$emit('edit', code);
    },
    showOnMap(code: string, name: string) {
      this.$emit('show-map', {code, name});
    },
  },
});
</script>

<style scoped>
.substation-table {
  width: 100%;
  border-collapse: collapse;
}

.substation-table th,
.substation-table td {
  border: 1px solid #ddd;
  padding: 8px;
}

.substation-table th {
  background-color: #444444;
  color: white;
  text-align: left;
}

.substation-table td {
  background-color: #333333;
  color: white;
}

.substation-table button {
  background-color: #444444;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  border-radius: 3px;
}

.substation-table button:hover {
  background-color: #555555;
}
</style>
