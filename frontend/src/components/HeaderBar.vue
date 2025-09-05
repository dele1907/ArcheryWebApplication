<script setup lang="ts">
import app_logo from '@/assets/icons/app_logo.png'

const onMenuButtonClick = () => {
  alert('Main menu button clicked!')
}
</script>

<template>
  <div class="header-bar-button-wrapper">
    <v-btn
      variant="elevated"
      density="comfortable"
      color="primary"
      icon="fa-bars"
      elevation="8"
      @click="onMenuButtonClick"
    >
    </v-btn>
  </div>

  <div class="header-bar-title-wrapper">
    <h1>Dashboard</h1>
  </div>

  <div class="header-bar-icon-wrapper">
    <img :src="app_logo" alt="App Logo" class="header-bar-icon" />
  </div>
</template>

<style scoped></style>
