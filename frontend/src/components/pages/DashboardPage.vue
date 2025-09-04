<script setup lang="ts">
import { ref } from 'vue'

export type Archer = {
  name: string
  firstName: string
  club: string
  clubNumber: number
  id: string
  bowType: string
  ageCategory: string
}
const archers = ref<Array<Archer>>([])
const URL = 'http://localhost:7070/archers'

const onGetAllArchers = async (): Promise<void> => {
  const response = await fetch(URL)

  archers.value = await response.json()
}
</script>

<template>
  <div data-testid="dashboard-page" class="page">
    <p>Welcome to the Archery Tournament Dashboard!</p>
    <button @click="onGetAllArchers">Get All Archers</button>

    <div class="archers-list">
      <ul v-if="archers.length > 0">
        <li v-for="archer in archers" :key="archer.id">
          <div class="archer-wrapper">
            {{ archer.name }} |
            {{ archer.firstName }} |
            {{ archer.club }} |
            {{ archer.bowType }} |
            {{ archer.ageCategory }}
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
  text-align: center;
}
</style>
