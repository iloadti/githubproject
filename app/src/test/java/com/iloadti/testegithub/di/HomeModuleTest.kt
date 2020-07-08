package com.iloadti.testegithub.di

import org.koin.test.AutoCloseKoinTest

/*

        Nao consegui ter um tempo maior para terminar a implementacao

*/
class HomeModuleTest : AutoCloseKoinTest(){

//    @Before
//    fun setupTest(){
//
//        val appModule = module {
//            single<Retrofit> { Mockito.mock(Retrofit::class.java) }
//        }
//
//        startKoin { modules(arrayListOf(appModule, AppModule().retrofit)) }
//    }
//
//
//    @Test
//    fun `Assert if repository is provide by module`() {
//        val repository by inject<SignRepository>()
//        Assert.assertNotNull(repository)
//    }
//
//    @Test
//    fun `Assert if useCase is provide by app`() {
//        val useCase by inject<SignUseCase>()
//        Assert.assertNotNull(useCase)
//    }
//
//    @Test
//    fun `Assert if viewModel is provide by app`() {
//        val viewModel by inject<SignViewModel>()
//        with(viewModel) {
//            assertNotNull(this)
//            assertEquals(SignViewModel::class.java, javaClass)
//        }
//    }
}