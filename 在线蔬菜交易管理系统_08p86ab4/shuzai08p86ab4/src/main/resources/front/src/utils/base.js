export const apiBaseUrl = 'http://localhost:8080'
export const isPreviewEnv = apiBaseUrl != 'http://localhost:8080'
const base = {
  get() {
    return {
      url: apiBaseUrl + '/shuzai08p86ab4/',
      name: 'shuzai08p86ab4',
    }
  },
}
export default base