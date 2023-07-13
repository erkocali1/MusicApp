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
 <img src="https://github.com/erkocali1/MusicApp/blob/master/app/src/main/res/drawable/3.png" alt="Resim">
   </p>
  <img src="https://github.com/erkocali1/MusicApp/blob/master/app/src/main/res/drawable/4.png" alt="Resim">

  <h2>📝Pagination Nedir </h2>
  Kotlin'de pagination, genellikle veri tabanlarından veya API çağrılarından dönen büyük miktardaki verileri daha küçük parçalara bölmek için kullanılır. Böylece kullanıcılar sayfa sayfa verilere erişebilir ve büyük veri kümesinin tamamını yüklemek zorunda kalmazlar.
Kotlin'de pagination uygularken, veri kaynağından gelen verileri sayfa sayfa almak ve kullanıcıya göstermek için özel bir mantık kullanmanız gerekebilir. Genellikle sayfalama numarasına ve sayfa boyutuna göre bir sorgu veya filtreleme işlemi yaparak istenen veri parçasını alırsınız. Yaptığım bu uygulamamda da İtunes API kullanarak gelen verileri sayfa sayfa alarak büyük veri kümelerini yönetmeye çalıştım .Kaynak kodlarımı inceleyerek nasıl Pagination yapıldıgı konusu hakkında fikir sahibi olabilirsiniz ancak bir görsel ile genel mantığı anlatabilirim.

 <img src="https://github.com/erkocali1/MusicApp/blob/master/app/src/main/res/drawable/ss/paginationss.png" alt="Resim">


  
