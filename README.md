# renta-team-task


### Project structure
```
.
├── data
│   ├── AppDataBase.java
│   └── UserDao.java
├── di
│   ├── AppComponent.java
│   ├── componets
│   │   └── UsersSubComponent.java
│   ├── modules
│   │   ├── DataBaseModule.java
│   │   ├── NetworkModule.java
│   │   └── SubComponentsModule.java
│   └── scopes
│       ├── ActivityScope.java
│       └── FragmentScope.java
├── MyApplication.java
├── network
│   └── ReqResApi.java
├── pojo
│   ├── JResponse.java
│   └── JUser.java
├── repository
│   ├── AppRepository.java
│   ├── UsersLocalRepo.java
│   └── UsersRemoteRepo.java
├── ui
│   ├── adapters
│   │   └── UserListRecyclerViewAdapter.java
│   ├── MainActivity.java
│   ├── pages
│   │   ├── AboutFragment.java
│   │   ├── ContainerUserFragment.java
│   │   ├── ListUserFragment.java
│   │   └── UserFragment.java
│   └── viewmodels
│       └── UserViewModel.java
├── utils
│   └── Variables.java
└── validators
    └── NetworkValidator.java
```
