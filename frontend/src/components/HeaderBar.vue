<script setup lang="ts">
import app_logo from '@/assets/icons/app_logo.png';
import { useRoute } from 'vue-router';
import router from '@/router';
import { useTheme } from 'vuetify';

const onMenuButtonClick = () => {
  alert('Main menu button clicked!');
};

const getIconBasedOnTheme = () => {
  return theme.global.name.value === 'light' ? 'fa-moon' : 'fa-sun';
};

const theme = useTheme();
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
    <h1 @click="router.push('/')">{{ useRoute().meta.title }}</h1>
  </div>

  <div class="header-bar-theme-toggle-button-wrapper">
    <v-btn
      variant="elevated"
      class="header-bar-theme-toggle-button"
      density="comfortable"
      color="primary"
      :icon="getIconBasedOnTheme()"
      elevation="8"
      @click="theme.toggle()"
    >
    </v-btn>
  </div>

  <div class="header-bar-icon-wrapper">
    <img :src="app_logo" alt="App Logo" class="header-bar-icon" />
  </div>
</template>

<style scoped>
.header-bar-title-wrapper {
  h1 {
    cursor: pointer;
  }
}

.header-bar-theme-toggle-button-wrapper {
  .header-bar-theme-toggle-button {
    position: relative;
    right: 10px;
  }
}
</style>
