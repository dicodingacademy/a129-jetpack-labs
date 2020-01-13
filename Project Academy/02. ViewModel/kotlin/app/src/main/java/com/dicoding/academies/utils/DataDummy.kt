package com.dicoding.academies.utils

import com.dicoding.academies.data.CourseEntity
import com.dicoding.academies.data.ModuleEntity

import java.util.ArrayList

object DataDummy {

    fun generateDummyCourses(): ArrayList<CourseEntity> {

        val courses = ArrayList<CourseEntity>()

        courses.add(CourseEntity("a14",
                "Menjadi Android Developer Expert",
                "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
                "100 Hari",
                false,
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg"))
        courses.add(CourseEntity("a55",
                "Kotlin Android Developer Expert",
                "Pada Google I/O 2017, Kotlin diumumkan sebagai bahasa pemrograman yang termasuk dalam bahasa kelas satu (First class) yang didukung untuk pembuatan aplikasi Android, selain Java dan C++. Kotlin adalah bahasa pemrograman yang dibuat oleh JetBrains. Google juga akan memastikan bahwa semua fitur baru di Android, framework, IDE dan keseluruhan library, akan dapat bekerja dan terintegrasi baik dengan bahasa pemrograman Kotlin serta interopable dengan fungsi-fungsi Java yang telah ada sehingga memungkinkan para engineer melakukan perubahan bagian tertentu aplikasi dari Java ke Kotlin dan sebaliknya dengan sangat mudah.",
                "50 Hari",
                false,
                "https://www.dicoding.com/images/small/academy/kotlin_android_developer_expert_logo_070119140227.jpg"))
        courses.add(CourseEntity("a47",
                "Menjadi Game Developer Expert",
                "Semua modul dalam kelas ini telah diverifikasi langsung oleh Asosiasi Game Indonesia (AGI) untuk memastikan materi yang diajarkan relevan dan sesuai dengan kebutuhan industri game saat ini. Peserta akan belajar best practice membuat game seperti Script, Sprite, UI, Gameplay, Input Method, Porting ke Android / iOS, Modul Services (Collaboration, Ads, Analytics dan Google Play Games - Update Oktober 2018), Porting ke VR - Cardboard dan Gear VR (New Mei 2018) dengan Unity 3D. Peserta juga akan belajar langsung membuat 8 game yaitu Casual (Update Agustus 2018), Pilah Sampah (New November 2018), Tower Defense (New November 2018), Arcade (Update Maret 2018), Platformer (Update Januari 2019), FPS, Multiplayer (Update Maret 2018), serta Game Interaktif dengan VR (New Mei 2018).",
                "75 Hari",
                false,
                "https://www.dicoding.com/images/small/academy/menjadi_game_developer_expert_logo_070119140532.jpg"))
        courses.add(CourseEntity("a74",
                "Membangun Progressive Web Apps",
                "Progressive Web Apps adalah aplikasi web yang memanfaatkan beragam fitur web modern sehingga dapat menyajikan pengalaman pengguna seperti aplikasi native. PWA mengubah sajian tampilan yang umumnya dibuka melalui halaman browser menjadi jendela aplikasi tersendiri. Selain itu PWA juga memungkinkan konten halaman diakses dalam mode offline, menampilkan pesan pemberitahuan, hingga akses ke hardware dari perangkat seperti halnya native app.",
                "50 Hari",
                false,
                "https://www.dicoding.com/images/small/academy/membangun_progressive_web_apps_logo_070119142922.jpg"))
        courses.add(CourseEntity("a51",
                "Belajar Membuat Aplikasi Android untuk Pemula", "Kelas ini didesain oleh Google Authorized Training Partner untuk developer Android di Indonesia. Peserta akan mempelajari materi dasar Android dalam 30 hari dan diarahkan untuk membuat aplikasi sederhana. Terdapat 27 modul yang juga merupakan bagian dari Kelas Menjadi Android Developer Expert. Peserta disarankan setidaknya memiliki pengetahuan tentang programming Java dan atau pemrograman berorientasi objek. Sistem pembelajaran adalah online (dapat diakses kapanpun dan darimanapun selama tersedia internet) dan kehadiran tatap muka tidak diperlukan. Tools yang diwajibkan untuk kelas belajar Android ini adalah Android Studio. Peserta harus submit satu proyek akhir yang akan direview oleh developer expert untuk mendapatkan sertifikat dari kelas ini. Bila menginginkan materi yang lebih komprehensif, silahkan mengikuti kelas Menjadi Android Developer Expert.",
                "30 Hari",
                false,
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg"))

        return courses
    }

    fun generateDummyModules(courseId: String): ArrayList<ModuleEntity> {

        val modules = ArrayList<ModuleEntity>()

        modules.add(ModuleEntity(
                "{$courseId}m1",
                courseId,
                "Modul 0 : Introduction",
                0))
        modules.add(ModuleEntity("{$courseId}m2",
                courseId,
                "Modul 1 : Teori 1",
                1, false))
        modules.add(ModuleEntity("{$courseId}m3",
                courseId,
                "Latihan 1",
                2, false))
        modules.add(ModuleEntity("{$courseId}m4",
                courseId,
                "Bedah Kode 1",
                3, false))
        modules.add(ModuleEntity("{$courseId}m5",
                courseId,
                "Modul 2 : Teori 2",
                4, false))

        modules.add(ModuleEntity("{$courseId}m6",
                courseId,
                "Latihan 2",
                5, false))

        modules.add(ModuleEntity("{$courseId}m7",
                courseId,
                "Bedah Kode 2",
                6, false))

        return modules
    }
}