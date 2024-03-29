package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;

import java.nio.file.Path;
import java.util.List;

public class Cotuba {

    public void executa(String formato, Path diretorioDosMD, Path arquivoDeSaida){
        RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);

        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);

        GeradorEbook gerador;

        if ("pdf".equals(formato)) {
            gerador = GeradorPDF.getInstance();
        } else if ("epub".equals(formato)) {
            gerador = GeradorEPUB.getInstance();
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

        gerador.gera(ebook);
    }
}
