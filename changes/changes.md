## Cambios

En el enum `NavScreenAdventurer` que esta en el package `Navigation`
```kt
public enum NavScreenAdventurer {
    welcome_screen,
    login_screen,
    select_role_screen,
    signup_screen,
    home_screen,//Agregas esta linea
} 
```

En la interfaz `Placeholder` que está en el package `utils/interfaces`
```kt

interface Placeholder {
    @POST("authentication/sign-up")
    suspend fun singUp(@Body request: UserRequestSignUp):Response<UserResponse>

    @POST("authentication/sign-in")
    suspend fun singIn(@Body request: UserRequestSignIn):Response<UserResponse>

    //Agregas esto:
    @GET("publication/all-publications")
    suspend fun listPosts():Response<List<PostResponse>>

}
```
En el object `RetrofitClient` que está en el package `utils` 
```kt
object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8080/api/v1/"
    //Todo lo que esta abajo 👇, lo copias y pegas
    private var authToken: String? = null
    private val gson: Gson = GsonBuilder().create()
    private val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
        authToken?.let {
            request.addHeader("Authorization", "Bearer $it")
        }
        chain.proceed(request.build())
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val placeholder: Placeholder = retrofit.create(Placeholder::class.java)
    fun setAuthToken(token: String?) {
        authToken = token
    }

}
```
En la clase `LoginViewModel` que está en el package `domains/authentication/screens/states`
```kt
//A esto le quitas el val
 val userLogged = UserLogged(
                id = userResponse.id,
                username = userResponse.username,
                token = userResponse.token
            )
// y que termine así:
            userLogged = UserLogged(
                id = userResponse.id,
                username = userResponse.username,
                token = userResponse.token
            )

```
y luego de la linea 
```kt
state = state.copy(loginSuccess = true, userLogged = userLogged, errorMessage = null)
```
 pegas esto:
```kt
RetrofitClient.setAuthToken(userLogged.token)
```

En la fun LogInScreen que está en `domains/authentication/screens` en el botón:
```kt
//En el onClick pones todo lo que se ve ahí
Button(
            onClick = {
                viewModel.viewModelScope.launch {
                    viewModel.signInUser(state.username, state.password)
                    if (viewModel.state.loginSuccess) {
                        navController.navigate(NavScreenAdventurer.home_screen.name)
                    }
                }

            }
        ) {
            Text("Login", fontSize = 22.sp, fontFamily = cabinFamily, fontWeight = FontWeight.Bold)
        }
```

En `ui.theme` agregas esto:
```kt
val Cream = Color(0xfff3d39e)
val Brown = Color(0xff765532)
```
En `AdventurerNavigation` agregas esto al final:
```kt
composable(NavScreenAdventurer.home_screen.name){
            val viewModel: HomeViewModel = viewModel()
            HomeScreen(viewModel = viewModel, navController = navController)
        }
```


