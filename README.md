# bora-bor-odev2
https://user-images.githubusercontent.com/88214480/188313778-a5e18fa9-6f29-4967-a512-c87828bd1007.mp4

# Eager vs Lazy Filters
Eager kelimesi bir algoritmanın hemen yürütülerek sonuç döndürmesi anlamına gelir. Lazy ise algoritmanın yürütülmesi gereken zamana kadar hesaplamayı ertelemesi ve ardından bir sonuç döndürmesi anlamına gelir.

Eager ve lazy algoritmaların hem artıları hem de eksileri vardır. Eager algoritmaları anlamak ve debug etmek daha kolaydır. Ayrıca tek bir kullanım durumu (örneğin filtreleme) için yüksek oranda optimize edilebilirler. Lazy algoritmalar ise bazen daha az hesaplama gerektirir ve hesaplamada birden fazla adım varsa (ör. filtreleme, mapleme, küçültme), bellekte daha az yer kaplar.

Eager filtering ve lazy filtering'de bu şekilde işler. Eager filtering uygulanan liste üzerinde filtreleme işlemi hemen yürütülerek sonuç döndürülür. Lazy filtering uygulanan bir listede ise listeye erişim sağlanmadıkça filtering işlemi uygulanmaz.

## Eager filtering örneği 
```kotlin
val kisiListesi = listOf("Ahmet", "Mehmet", "Ayse", "Ali", "Ceyda", "Semih")
val eagerList = kisiListesi.filter { it.first() == 'A' } // filtreleme işlemi uygulanır ve sonuç döndürülür
println(eagerList)
```

## Lazy filtering örneği
```kotlin
val kisiListesi = listOf("Ahmet", "Mehmet", "Ayse", "Ali", "Ceyda", "Semih")
val eagerList = kisiListesi.asSequence().filter { it.first() == 'A' } // filtreleme işlemi listeye erişilene kadar uygulanmaz
println(eagerList) // filtreleme işlemi uygulanmaz
println(eagerList.toList()) // filtreleme işlemi uygulanır ve sonuç döndürülür
```
