package be.collin.domain

data class GenreLayout(val genreName: Genre,
                       val engine: Rating,
                       val gamePlay: Rating,
                       val storyQuests: Rating,
                       val dialogues: Rating,
                       val levelDesign: Rating,
                       val ai: Rating,
                       val worldDesign: Rating,
                       val graphics: Rating,
                       val sound: Rating)
