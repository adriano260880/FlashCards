package br.com.aal.clashcards.controller;

import br.com.aal.clashcards.service.FlashCardsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FlashCardsController {


    private final FlashCardsService service;

    private final FlashCardsService verbsService;

    private final FlashCardsService substantivesService;

    public FlashCardsController(@Qualifier("FlashCardsImpl") FlashCardsService service,
                                @Qualifier("VerbsFlashCardsImpl") FlashCardsService verbsService,
                                @Qualifier("SubstantivesFlashCardsImpl") FlashCardsService substantivesService) {
        this.service = service;
        this.verbsService = verbsService;
        this.substantivesService = substantivesService;
    }

    @GetMapping("/cards")
    public String getCard() throws IOException {
        return """
                <!DOCTYPE html>
                <html>
                  <head>
                    <meta charset=utf-8/>
                    <title>Cards</title>
                  </head>
                  <body>
                    <table align="center">
                      <tr>
                        <td align="center">
                          <h1>
                          
                          """+this.service.getCard().get(0)+"""                          
                            
                          </h1>
                        </td>
                      </tr>
                    </table>
                  </body>
                  <script>
                        setTimeout(()=> {
                  	  var teste3 = document.getElementById("result").innerHTML;
                           document.getElementById("result").innerHTML = '<span style="color:black;">'+teste3+'</span>';
                        }
                        ,5000);
                     </script>
                  <style>
                td
                {
                  vertical-align:middle;
                  height:500px;
                }
                </style>
                </html>
                """;
    }

    @GetMapping("/verbs")
    public String getVerbsCard() throws IOException {
        return """
                <!DOCTYPE html>
                <html>
                  <head>
                    <meta charset=utf-8/>
                    <title>Verbs/title>
                  </head>
                  <body>
                    <table align="center">
                      <tr>
                        <td align="center">
                          <h1>
                          
                          """+this.verbsService.getCard()+"""                          
                            
                          </h1>
                        </td>
                      </tr>
                    </table>
                  </body>
                  <script>
                        setTimeout(()=> {
                  	  var teste3 = document.getElementById("result").innerHTML;
                           document.getElementById("result").innerHTML = '<span style="color:black;">'+teste3+'</span>';
                        }
                        ,5000);
                     </script>
                  <style>
                td
                {
                  vertical-align:middle;
                  height:500px;
                }
                </style>
                </html>
                """;
    }

    @GetMapping("/substantives")
    public String getSubstantivesCard() throws IOException {
        List<Object> objectList = this.substantivesService.getCard();
        return """
                <!DOCTYPE html>
                <html>
                  <head>
                    <meta charset=utf-8/>
                    <title>Substantives</title>
                  </head>
                  <body>
                    <table align="center">
                      <tr>
                        <td align="center">
                          <h1>
                          
                          """+objectList.get(0)+"""                          
                            
                          </h1>
                          <img src="http://localhost:8000/"""+objectList.get(1)+".png"+"""
                       
                          
                          " alt="Trulli" width="500" height="333">
                        </td>
                      </tr>
                    </table>
                  </body>
                  <script>
                        setTimeout(()=> {
                  	  var element = document.getElementById("result").innerHTML;
                           document.getElementById("result").innerHTML = '<span style="color:black;">'+element+'</span>';
                        }
                        ,5000);
                     </script>
                  <style>
                td
                {
                  vertical-align:middle;
                  height:500px;
                }
                </style>
                </html>
                """;
    }

    @GetMapping("/refresh")
    public void refreshCards() {
        service.refresh();
        substantivesService.refresh();
        verbsService.refresh();
    }
}
