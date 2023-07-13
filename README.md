# 🎵 MusicApp

✓ MusicApp with Dagger Hilt ( Data-Domain- UI Layer )

 <img src="https://github.com/erkocali1/MusicApp/blob/master/app/src/main/res/drawable/ft1.jpg" alt="Resim">

 </head>
<body>
  <h1>🖊️Uygulamya Ön Bakış</h1>
  <p>
    Bu Kotlin uygulaması, iTunes API'den alınan verilerle oluşturulmuştur. Bu Kotlin uygulamasında, veri işleme, iş mantığı ve kullanıcı arayüzü ile ilgili sorumlulukları ayrı katmanlara bölmek için Data, Domain ve UI katmanlarını kullanmayı tercih ettim. Verilerin API'den alınması ve işlenmesi için çeşitli framework'ler ve kütüphaneler kullandım. Ayrıca, kullanıcı deneyimini iyileştirmek için sayfalama (pagination) yöntemi de uygulanmıştır. Bu yaklaşım, uygulamanın verileri verimli bir şekilde yönetmesini ve büyük veri kümeleriyle başa çıkmasına yardımcı olması amaçlanmıştır.
  </p>
  <p>
    Proje, birçok kütüphane ve bileşen kullanılarak geliştirilmiştir ve güncel teknolojilerle kullanıcı ve uygulama etkileşimini en hızlı şekilde sağlamayı hedeflemektedir. Tasarımı kullanıcı etkileşimini ön planda tutacak şekilde yapılmıştır. Uygulama, kullanıcı dostu bir arayüz ve etkileşimli özellikler sunmak için özenle geliştirilmiştir.
  </p>
 <h2>📝 Kullanılan Componentler</h2>
<ul>
  <li>Pagination</li>
  <li>Dagger Hilt</li>
  <li>Data-Domain-UI Layer</li>
  <li>Retrofit</li>
  <li>Room</li>
  <li>Navigation Component</li>
  <li>Splash Screen API</li>
  <li>Coroutine</li>
  <li>Flow</li>
  <li>LifeCycle</li>
  <li>Shared Preferences</li>
  <li>Coil</li>
</ul>
</body>
 <h2>📱 Sayfalar</h2>
   </p>
 <img src="https://github.com/erkocali1/MusicApp/blob/master/app/src/main/res/drawable/ss/glow%20(1).png" alt="Resim">
   </p>
  <img src="https://github.com/erkocali1/MusicApp/blob/master/app/src/main/res/drawable/ss/glow%20(2).png" alt="Resim">

  <h2>📝Pagination Nedir </h2>
  Kotlin'de pagination, genellikle veri tabanlarından veya API çağrılarından dönen büyük miktardaki verileri daha küçük parçalara bölmek için kullanılır. Böylece kullanıcılar sayfa sayfa verilere erişebilir ve büyük veri kümesinin tamamını yüklemek zorunda kalmazlar.
Kotlin'de pagination uygularken, veri kaynağından gelen verileri sayfa sayfa almak ve kullanıcıya göstermek için özel bir mantık kullanmanız gerekebilir. Genellikle sayfalama numarasına ve sayfa boyutuna göre bir sorgu veya filtreleme işlemi yaparak istenen veri parçasını alırsınız. Yaptığım bu uygulamamda da İtunes API kullanarak gelen verileri sayfa sayfa alarak büyük veri kümelerini yönetmeye çalıştım .Kaynak kodlarımı inceleyerek nasıl Pagination yapıldıgı konusu hakkında fikir sahibi olabilirsiniz ancak bir görsel ile genel mantığı anlatabilirim.
 <img src="https://github.com/erkocali1/MusicApp/blob/master/app/src/main/res/drawable/ss/paginationss.png" alt="Resim">
 Kaynak:https://developer.android.com/topic/libraries/architecture/paging/v3-overview

   <h2>📝Data-Domain-UI Layer</h2>
   
   Data, Domain ve UI katmanları, yazılım mimarisi konseptlerinden biri olan MVVM (Model-View-ViewModel) ve Clean Architecture prensipleriyle ilişkilidir. Her bir katman, belirli bir sorumluluk ve iş mantığı için tasarlanmıştır.MVVM (Model-View-ViewModel) mimarisi, UI Layer (Activity, Fragment vb.) ve ViewModel arasında bir bağlantı noktası sağlar. ViewModel, kullanıcının arayüz ile etkileşimini yönetir, gerekli verileri Domain Layer'dan alır ve UI Layer'a sunar. ViewModel, UI durumunu takip eder ve verilerin doğru bir şekilde güncellenmesini sağlar.Clean Architecture prensipleri, bu katmanların bağımsız olmasını ve birbirlerine bağımlılık oluşturmamalarını sağlar. Bu sayede, her katmanı ayrı ayrı geliştirmek, test etmek ve değiştirmek kolaylaşır.Benim bu projede uyguladıgım bu pattern başta uygulamada bir takım bussines logic leri yönetmenizi gerektitiyor ancak daha sonrasında yeni bir şey ekleme ve test edilebilirlik açısından işimi çok kolaylaştırıyordu.Kısıca kullandığım yapılarla bunları ilişkilendiricek olursam:
   <h3>Data Katmanı:</h3>

<ul>
  <li><strong>Dagger Hilt:</strong> Bağımlılıkları yönetmek ve enjeksiyon yapısı sağlamak için data katmanında kullanılabilir.</li>
  <li><strong>Retrofit:</strong> Veri API'si ile iletişim kurmak ve veri alışverişi yapmak için data katmanında kullanılabilir.</li>
  <li><strong>Room:</strong> Veritabanı işlemlerini yönetmek, verileri saklamak ve erişmek için data katmanında kullanılabilir.</li>
  <li><strong>Coroutine:</strong> Asenkron işlemleri yönetmek ve verileri işlemek için data katmanında kullanılabilir.</li>
  <li><strong>Flow:</strong> Veri akışını yönetmek ve reaktif programlama yapmak için data katmanında kullanılabilir.</li>
  <li><strong>Shared Preferences:</strong> Küçük verileri saklamak ve yönetmek için data katmanında kullanılabilir.</li>
</ul>

<h3>Domain Katmanı:</h3>

<ul>
  <li><strong>Pagination:</strong> Veri akışını yönetmek, sayfalama işlemlerini gerçekleştirmek ve verilerin etkili bir şekilde yönetilmesini sağlamak için domain katmanında kullanılabilir.</li>
</ul>

<h3>UI Katmanı:</h3>

<ul>
  <li><strong>Navigation Component:</strong> Uygulama içinde gezinme ve sayfa yönlendirmeleri için UI katmanında kullanılabilir.</li>
  <li><strong>Splash Screen API:</strong> Uygulama başlangıcında bir açılış ekranı oluşturmak ve kullanıcı deneyimini iyileştirmek için UI katmanında kullanılabilir.</li>
  <li><strong>Coroutine:</strong> Asenkron işlemleri yönetmek, iş parçacığı yönetimini iyileştirmek ve UI etkinliklerini engellemeden verileri işlemek için UI katmanında kullanılabilir.</li>
  <li><strong>LifeCycle:</strong> Uygulamanın yaşam döngüsünü izlemek ve uygun aksiyonlar almak için UI katmanında kullanılabilir.</li>
  <li><strong>Coil:</strong> Resim yüklemeyi ve görüntüleri işlemeyi kolaylaştırmak için UI katmanında kullanılabilir.</li>
</ul>
<img src="https://github.com/erkocali1/MusicApp/blob/master/app/src/main/res/drawable/ss/celartsss.png" alt="Resim" style="width: 1100px; height:350px;">

 
 Kaynak:https://developer.android.com/topic/architecture

 

https://github.com/erkocali1/MusicApp/assets/116030125/9f73b734-d968-45c5-b2f4-227ca4ba4736


## 📝: Dependency
```
dependencies {
    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // http client
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    //interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Navigation Components
    implementation "androidx.navigation:navigation-fragment-ktx:2.5.3"
    implementation "androidx.navigation:navigation-ui-ktx:2.5.3"

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'

    // Coroutine Lifecycle Scopes
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"

    //BottomBar
    implementation 'com.github.ismaeldivita:chip-navigation-bar:1.4.0'

    //circle
    implementation 'de.hdodenhof:circleimageview:3.1.0'

    //pagination
    def paging_version = "3.1.1"
    implementation "androidx.paging:paging-runtime:$paging_version"

     //Coil
    implementation 'io.coil-kt:coil:1.3.2'

    //room
    implementation 'androidx.room:room-runtime:2.5.2'
    kapt 'androidx.room:room-compiler:2.5.2'
    annotationProcessor "androidx.room:room-compiler:2.5.2"
    implementation "androidx.room:room-ktx:2.5.2"

    //splashScreen
    implementation "androidx.core:core-splashscreen:1.0.1"

}
```






   
   


  
