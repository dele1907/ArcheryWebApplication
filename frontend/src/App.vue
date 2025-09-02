<script setup lang="ts">

import DashboardPage, {type Archer} from "@/components/DashboardPage.vue";

const URL = 'http://localhost:7070/archers'

const getAllArchers = async (): Promise<Array<Archer>> => {
  const response = await fetch(URL);

  return await response.json();
}


</script>

<template>
  <DashboardPage :onGetAllArchers="getAllArchers" />
</template>

<style scoped>
</style>
