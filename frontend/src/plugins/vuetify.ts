import 'vuetify/styles'
import { createVuetify } from 'vuetify'

// Optional: Icon-Sets (Material Design Icons, etc.)
import { aliases } from 'vuetify/iconsets/mdi'

export default createVuetify({
  icons: {
    defaultSet: 'fontawesome',
    aliases,
    sets: {
      fontawesome: {
        component: () => import('@fortawesome/vue-fontawesome').then(m => m.FontAwesomeIcon),
      }
    },
  },
  theme: {
    defaultTheme: 'light',
  },
})
