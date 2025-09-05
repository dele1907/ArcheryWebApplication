<script setup lang="ts">
import { ApiHelper } from '@/helpers/api/apihelper.ts'
import { onMounted, ref } from 'vue'
import type { Archer } from '../../../types/types.ts'
import ShooterInfoCard from '@/components/ShooterInfoCard.vue'

const shooters = ref<Array<Archer>>([])

onMounted(async () => {
  shooters.value = await ApiHelper.fetchShooters()
})
</script>

<template>
  <div data-testid="shooters-management-page" class="page">
    <div class="shooter-info-cards-wrapper">
      <div v-for="shooter in shooters" :key="shooter.id" class="shooter-card-wrapper">
        <ShooterInfoCard :shooter="shooter" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.shooter-info-cards-wrapper {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>
