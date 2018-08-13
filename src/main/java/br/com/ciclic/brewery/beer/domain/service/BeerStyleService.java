package br.com.ciclic.brewery.beer.domain.service;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.BreweryTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.JukeBoxTransferObject;
import br.com.ciclic.brewery.beer.domain.adapter.BeerStyleAdapter;
import br.com.ciclic.brewery.beer.domain.decorator.BeerStyleDecorator;
import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import br.com.ciclic.brewery.beer.domain.validation.BeerStyleValidation;
import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.SpotifyClient;
import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.Playlist;
import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.PlaylistError;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class BeerStyleService {

    @Autowired
    private SpotifyClient client;

    @Autowired
    private BeerStyleRepository repository;

    @Autowired
    private BeerStyleValidation validation;

    public String add(BeerStyleTransferObject to) throws Exception {
        validation.validateRepeatedName(to.getName());

        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle beerStyle = adapter.converterEntity();
        beerStyle = repository.insert(beerStyle);

        return beerStyle.getId();
    }

    public void edit(String id, BeerStyleTransferObject to) throws Exception {
        validation.validateBeerStyleExist(id);
        validation.validateRepeatedName(id, to.getName());

        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle beerStyle = adapter.converterEntity();
        beerStyle.setId(id);
        repository.save(beerStyle);
    }

    public void delete(String id) throws Exception {
        validation.validateBeerStyleExist(id);
        repository.delete(id);
    }

    public BeerStyleTransferObject find(String id) throws Exception {
        validation.validateBeerStyleExist(id);

        BeerStyle entity = repository.findOne(id);
        return new BeerStyleAdapter(entity).converterTransferObject();
    }

    public BreweryTransferObject findAll() throws Exception {
        List<BeerStyle> entities = repository.findAll();
        validation.validateBeerStyleExist(entities);

        List<BeerStyleTransferObject> list = entities.stream()
                                                     .map( entity -> new BeerStyleAdapter(entity).converterTransferObject())
                                                     .collect(Collectors.toList());

        return new BreweryTransferObject(list);
    }

    public JukeBoxTransferObject findByTemperature(Integer temperature) throws Exception {
        List<BeerStyle> entities = repository.findAll();
        validation.validateBeerStyleExist(entities);

        SortedSet<BeerStyle> entitiesSorted = new TreeSet<>(entities);

        BeerStyle entity = entitiesSorted.parallelStream()
                                    .map(e -> new BeerStyleDecorator(e, temperature))
                                    .collect(Collectors.toList())
                                    .stream()
                                    .min((d1, d2) -> Integer.compare(d1.getTemperatureDifference(), d2.getTemperatureDifference()))
                                    .map(d -> d.getBeerStyle())
                                    .get();

        Playlist playlist = client.findPlaylistsTracks(entity.getName());

        if (playlist == null) {
            return new JukeBoxTransferObject(entity.getName(), new PlaylistError(404, "Not found playlist."));
        }

        return new JukeBoxTransferObject(entity.getName(), playlist);
    }

}
