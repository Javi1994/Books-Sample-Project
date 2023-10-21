package com.javi.data.datasource.mock

import com.javi.data.datasource.remote.BookApi
import com.javi.data.dto.BookDetailDto
import com.javi.data.dto.BookDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookApiMock : BookApi {

    private val book1 = BookDto(title = "The Ruby Torch", author = "Hadia Hauzini")
    private val book2 = BookDto(title = "The Shadow Wolf", author = "Darren Ljubica")
    private val book3 = BookDto(title = "City of Grace", author = "Indrajit Valentinus")
    private val book4 = BookDto(title = "Grace and the Gate", author = "Shamsuddin Iakob")
    private val book5 = BookDto(title = "Scar and the Lily", author = "Jameson Vohu Manah")
    private val book6 = BookDto(title = "The crystal in the North", author = "Tanvi Juuso")

    override fun getFavouriteBooks(username: String): Flow<List<BookDto>> {
        return flow {
            delay(1000)
            emit(
                listOf(book1, book2, book3, book4, book5, book6)
            )
        }
    }

    override fun getAllBooks(): Flow<List<BookDto>> {
        return flow {
            delay(10000)
            emit(
                listOf(
                    book1,
                    book4,
                    book5,
                    book6,
                    book2,
                    book3,
                    book1,
                    book2,
                    book1,
                    book4,
                    book3,
                    book4,
                    book5,
                    book6,
                    book1,
                    book4,
                    book5,
                    book6,
                    book2,
                    book3,
                    book1,
                    book2,
                    book1,
                    book4,
                    book3,
                    book4,
                    book5,
                    book6,
                    book1,
                    book4,
                    book5,
                    book6,
                    book2,
                    book3,
                    book1,
                    book2,
                    book1,
                    book4,
                    book3,
                    book4,
                    book5,
                    book6
                )
            )
        }
    }

    override fun getBookDetail(id: String): Flow<BookDetailDto> {
        return flow {
            delay(1000)
            emit(
                BookDetailDto(
                    "1",
                    "Mocked Book",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ornare porta nisl non euismod. In hac habitasse platea dictumst. Sed tincidunt tempus sodales. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse ac nisi vitae sapien malesuada tincidunt. Quisque vel tortor at dolor scelerisque vulputate. Ut finibus posuere felis ac commodo. Etiam interdum condimentum diam quis lacinia. Phasellus vulputate dui tortor, id hendrerit velit lobortis nec. Sed facilisis scelerisque sapien in ornare. Phasellus dui arcu, tempus nec orci eget, blandit rhoncus lacus. Nam eu lacus ut lorem laoreet posuere sit amet at eros. Donec sed feugiat orci.\n" +
                            "\n" +
                            "Proin a viverra libero, nec malesuada dui. Fusce ligula lectus, sollicitudin id congue id, faucibus ac ipsum. Morbi quis ipsum eget velit mollis sodales eu sed erat. Phasellus sagittis ligula sed malesuada eleifend. Morbi tincidunt, nibh eget finibus auctor, ligula ligula placerat magna, quis feugiat neque augue nec mauris. Pellentesque mollis eros a tortor pellentesque rutrum. Proin pharetra sagittis leo, eget egestas leo convallis id.\n" +
                            "\n" +
                            "Donec eget bibendum dolor. Proin hendrerit, lacus mollis vehicula auctor, orci nisi rutrum justo, at porttitor ante erat ut sem. Morbi id aliquet ligula. Pellentesque ornare feugiat varius. Quisque vestibulum lectus vel ex ultricies bibendum. Donec vitae turpis urna. Maecenas in hendrerit neque, vitae lacinia turpis. Cras facilisis mi diam, ac bibendum mi efficitur nec.\n" +
                            "\n" +
                            "Etiam ac consectetur neque. Nunc ante massa, rutrum eu nibh consectetur, dictum viverra sapien. Donec non ligula risus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Pellentesque ut nisi ultricies, suscipit urna nec, pretium erat. Integer vitae turpis et turpis scelerisque viverra congue quis sem. Donec id quam ut augue sollicitudin sagittis. Vivamus maximus blandit est, sed efficitur tortor convallis nec. Nulla facilisi. Duis erat augue, interdum sed ornare nec, aliquet at ex. Nulla dictum, elit et faucibus pharetra, dui sapien aliquam sem, vel aliquam est sapien et lorem. Aliquam erat volutpat. Etiam eleifend gravida elit vel hendrerit. Sed nunc risus, volutpat in efficitur ut, cursus eu urna.\n" +
                            "\n" +
                            "Curabitur maximus euismod magna eget vulputate. Curabitur arcu urna, aliquet in felis volutpat, venenatis fringilla libero. Fusce bibendum pulvinar bibendum. Etiam gravida cursus nisi sed maximus. Fusce id vestibulum dolor. Fusce condimentum feugiat tortor non maximus. Pellentesque ultrices congue neque vitae sollicitudin. Phasellus vitae lacus efficitur, suscipit nulla in, rutrum arcu. Etiam eget accumsan velit.",
                    385,
                    2629,
                    "Me"
                )
            )
        }
    }
}