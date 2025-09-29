<script setup lang="ts">
import DashboardCard from '@/components/dashboardcomponents/DashboardCard.vue';
import dashboard_archer_overview from '@/assets/images/dashboard/dashboard_archer_overview_image.png';
</script>

<template>
  <div data-testid="dashboard-page" class="page">
    <div class="dashboard-menu-item-wrapper">
      <DashboardCard
        title="Schützenverwaltung"
        :image-src="dashboard_archer_overview"
        link="/shooters"
      />
      <DashboardCard title="Vereinsverwaltung" image-src="" link="/clubs" />
      <DashboardCard title="Turnierverwaltung" image-src="" link="/tournaments" />
      <DashboardCard title="Ergebniserfassung" image-src="" link="/result-input" />
    </div>
  </div>
</template>

<style scoped>
.dashboard-menu-item-wrapper {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>
