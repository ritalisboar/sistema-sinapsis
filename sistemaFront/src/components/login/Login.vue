<template>
  <WelcomeItem>
    <template #icon>  
      <AccountCircleIcon />
    </template> 
    <TextInput
      id="username-input" 
      v-model:value="username"
      label="Usuário:"
    />
    <p v-if="usernameError" class="error">{{ usernameError }}</p>
  </WelcomeItem>

  <WelcomeItem>
    <template #icon>
      <LockIcon />
    </template>
    <TextInput
      id="password-input" 
      v-model:value="password"
      label="Senha:"
      type="password"
    />
    <p v-if="passwordError" class="error">{{ passwordError }}</p>
  </WelcomeItem>
  
  <WelcomeItem>
    <template #icon>
      <LoginIcon />
    </template>
    <BaseButton 
      @click="handleSubmit"
      width="200px"
      height="50px"
    >
      Entrar
    </BaseButton>
  </WelcomeItem>
  
  <p v-if="successMessage" class="success">{{ successMessage }}</p>
</template>

<script setup lang="ts">
import WelcomeItem from '../WelcomeItem.vue'
import TextInput from '../textInput/TextInput.vue'
import BaseButton from '../button/Button.vue';

import AccountCircleIcon from '../icons/IconAccountCircle.vue'
import LockIcon from '../icons/IconLock.vue'
import LoginIcon from '../icons/IconLogin.vue'

import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { tagging } from '@/utils/Utils';

const username = ref('')
const password = ref('')
const usernameError = ref('')
const passwordError = ref('')
const successMessage = ref('')
const router = useRouter()

onMounted(() => {
  tagging('LoginView', 'showLoginView')
})

const validateFields = () => {
  tagging('LoginView', 'validateFields')
  usernameError.value = ''
  passwordError.value = ''
  successMessage.value = ''
  
  let isValid = true

  if (!username.value) {
    usernameError.value = 'Nome de usuário é obrigatório.'
    isValid = false
  }
  
  if (!password.value) {
    passwordError.value = 'Senha é obrigatória.'
    isValid = false
  }
  
  return isValid
}

const handleSubmit = () => {
  tagging('LoginView', 'handleSubmit')
  if (validateFields()) {
    successMessage.value = 'Login bem-sucedido!'
    setTimeout(() => {
      router.push({ name: 'home' })
    }, 1000)
  }
}
</script>

<style scoped>
.error {
  color: red;
  font-size: 14px;
  margin-top: 5px;
}

.success {
  color: green;
  font-size: 14px;
  margin-top: 10px;
}
</style>
