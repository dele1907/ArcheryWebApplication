<script setup lang="ts">
import {ref} from "vue";
import HeaderBar from "@/components/HeaderBar.vue";

export type Archer = {
  "name": string,
  "firstName": string,
  "club": string,
  "clubNumber": number,
  "id": string,
  "bowType": string,
  "ageCategory": string
}

const props = defineProps<{onGetAllArchers: () => Promise<Array<Archer>>}>();

const archers = ref<Array<Archer>>([]);

const onGetAllArchers = async () => {
  archers.value = await props.onGetAllArchers();
};
</script>

<template>
  <div data-testid="dashboard-page" class="page">
    <div class="page-header">
        <HeaderBar/>
    </div>

    <p>Welcome to the Archery Tournament Dashboard!</p>

    <button @click=onGetAllArchers >Get All Archers</button>

    <div class="archers-list">
      <ul v-if="archers.length > 0">
        <li v-for="archer in archers" :key="archer.id">
          <div class="archer-wrapper">
            {{ archer.name }}
            {{archer.firstName}}
            {{ archer.club }}
          </div>
        </li>
      </ul>

      <p v-else>No archers to display yet.</p>
    </div>
  </div>
</template>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}

.archer-wrapper {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
