package com.training.dicodingsubmitpart2


object DataStudents {
    private val namesOfUS = arrayOf("Hamza Firdaus",
        "Arnold Prakoso",
        "Albert Waseda",
        "Ghozali ghozalu",
        "Christopher",
        "Ian Dooley",
        "Gonzales",
        "Josep",
        "Jurica",
        "Seth Maharani")

    private val imageOfUs = intArrayOf(R.drawable.sy3,
        R.drawable.arnolt,
        R.drawable.albert,
        R.drawable.ghozali,
        R.drawable.christoper,
        R.drawable.ian_dooley,
        R.drawable.a,
        R.drawable.josep,
        R.drawable.jurica,
        R.drawable.seth)

    private val umurOfUs = intArrayOf(20, 21, 22, 20, 19, 25, 27, 23, 21, 20)

    private val kelasOfUs = arrayOf("Android Developer",
        "Web Developer",
        "Machine Learning Developer",
        "Android Developer",
        "Web Developer",
        "Machine Learning Developer",
        "Android Developer",
        "Web Developer",
        "Machine Learning Developer",
        "Android Developer")

    private val alamatOfUS = arrayOf("Jakarta",
        "Depok",
        "Bali",
        "Bandung",
        "Sydney",
        "United States",
        "Britain",
        "NTT",
        "New Zealend",
        "Swedish")

    private val hobbyOfUS = arrayOf("BasketBall","VollyBall","Gamers","Footbal","Editing","Vacation","Badminton","Golf","Bowling","Swimming")
    private val hobbyOfUS2 = arrayOf("Swimming","BasketBall","VollyBall","Gamers","Footbal","Editing","Vacation","Badminton","Golf","Bowling")
    private val hobbyOfUS3 = arrayOf("Bowling","Swimming","BasketBall","VollyBall","Gamers","Footbal","Editing","Vacation","Badminton","Golf")

    val listData: ArrayList<Students>
        get() {
            val list = arrayListOf<Students>()
            for (position in namesOfUS.indices) {
                val glimps = Students()
                glimps.apply {
                    nama = namesOfUS[position]
                    alamat = alamatOfUS[position]
                    umur = umurOfUs[position]
                    kelas = kelasOfUs[position]
                    image = imageOfUs[position]

                    chip1 = hobbyOfUS[position]
                    chip2 = hobbyOfUS2[position]
                    chip3 = hobbyOfUS3[position]
                    list.add(glimps)
                }
            }
            return list
        }
}