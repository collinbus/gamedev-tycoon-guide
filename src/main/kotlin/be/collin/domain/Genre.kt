package be.collin.domain

data class Genre(val genreName: String,
                 val engine: Rating,
                 val gamePlay: Rating,
                 val storyQuests: Rating,
                 val dialogues: Rating,
                 val levelDesign: Rating,
                 val ai: Rating,
                 val worldDesign: Rating,
                 val graphics: Rating,
                 val sound: Rating)
