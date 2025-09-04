<script setup lang="ts">

 const onMenuButtonClick = () => {
    alert('Main menu button clicked!');
 };

</script>

<template>
  <div class="header-bar-button-wrapper">
    <button data-testid="main-menu-button" class="header-bar-button" @click="onMenuButtonClick">
        <font-awesome-icon icon="fa-solid fa-bars" />
    </button>
  </div>

  <div class="header-bar-title-wrapper">
    <h1>Dashboard</h1>
  </div>

  <div class="header-bar-icon-wrapper">
    <img src="@/assets/icons/app_logo.png" alt="App Logo" class="header-bar-icon" />
  </div>
</template>

<style scoped>

</style>
