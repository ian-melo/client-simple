package net.ism.crud.clientsimple.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.ism.crud.clientsimple.controller.dto.tpp.IpVigilanteResponseDTO;
import net.ism.crud.clientsimple.controller.dto.tpp.MetaWeatherDetailListResponseDTO;
import net.ism.crud.clientsimple.controller.dto.tpp.MetaWeatherDetailResponseDTO;
import net.ism.crud.clientsimple.controller.dto.tpp.MetaWeatherSearchListResponseDTO;
import net.ism.crud.clientsimple.controller.dto.tpp.MetaWeatherSearchResponseDTO;
import net.ism.crud.clientsimple.model.Cliente;
import net.ism.crud.clientsimple.model.Estatistica;
import net.ism.crud.clientsimple.repository.EstatisticaRepository;
import net.ism.crud.clientsimple.util.HttpJsonUtil;
import net.ism.crud.clientsimple.util.constants.UrlFormatConstants;

@Controller
public class EstatisticaController {
	
	public static final boolean OPERATION_SUCCESS = true;
	public static final boolean OPERATION_FAIL = false;
	
	@Autowired
	private EstatisticaRepository estatisticaRepo;
	
	public boolean inserir(String ip, LocalDate data, Cliente cliente) {
		try {
			Estatistica estatistica = generateModel(ip, data, cliente);
			estatisticaRepo.save(estatistica);
			return OPERATION_SUCCESS;
		} catch(Exception e) { return OPERATION_FAIL; }
	}
	
	public boolean remover(Long clienteId) {
		try {
			Estatistica estatistica = estatisticaRepo.findByCliente_Id(clienteId);
			estatisticaRepo.deleteById(estatistica.getId());
			return OPERATION_SUCCESS;
		} catch(Exception e) { return OPERATION_FAIL; }
	}
	
	private Estatistica generateModel(String ip, LocalDate data, Cliente cliente) {
		// Info estatistica
		Estatistica est = new Estatistica();
		est.setIpv4(ip);
		est.setData(data);
		est.setCliente(cliente);
		
		// IP Vigilante - Search IP location
		String ipSearchUrl = String.format(UrlFormatConstants.FORMAT_IPVIGILANTE_COORDS_IP, ip);
		IpVigilanteResponseDTO ipSearch = HttpJsonUtil.doJsonGet(ipSearchUrl, IpVigilanteResponseDTO.class);
		if(ipSearch == null) return est;
		// Info
		String latitude = ipSearch.getData().getLatitude();
		String longitude = ipSearch.getData().getLongitude();
		est.setPais(ipSearch.getData().getCountryName());
		est.setCidade(ipSearch.getData().getCityName());
		est.setLatitude(Double.valueOf(latitude));
		est.setLongitude(Double.valueOf(longitude));
		
		// MetaWeather - Search location by coords (nearest distance)
		String locationSearchUrl = String.format(UrlFormatConstants.FORMAT_METAWEATHER_WOEID_SEARCH_COORDS, latitude, longitude);
		MetaWeatherSearchResponseDTO[] locationSearchArray = HttpJsonUtil.doJsonGet(locationSearchUrl, MetaWeatherSearchResponseDTO[].class);
		if(locationSearchArray == null) return est;
		// Sorting
		List<MetaWeatherSearchResponseDTO> locationSearchList = Arrays.asList(locationSearchArray);
		locationSearchList.sort(Comparator.comparingInt(MetaWeatherSearchResponseDTO::getDistance));
		MetaWeatherSearchResponseDTO locationBasic = locationSearchList.get(0); 
		
		// MetaWeather - Get location weather details
		String ano = String.valueOf(data.getYear());
		String mes = String.valueOf(data.getMonthValue());
		String dia = String.valueOf(data.getDayOfMonth());
		String woeid = locationBasic.getWoeid().toString();
		String locationDetailUrl = String.format(UrlFormatConstants.FORMAT_METAWEATHER_WOEID_DETAIL_DAY, woeid, ano, mes, dia);
		MetaWeatherDetailResponseDTO[] locationDetailArray = HttpJsonUtil.doJsonGet(locationDetailUrl, MetaWeatherDetailResponseDTO[].class);
		if(locationDetailArray == null) return est;
		// Sorting - max temp
		List<MetaWeatherDetailResponseDTO> locationDetailList = Arrays.asList(locationDetailArray);
		locationDetailList.sort(Comparator.comparingDouble(MetaWeatherDetailResponseDTO::getMax_temp).reversed());
		Double tempMaxima = locationDetailList.get(0).getMax_temp();
		// Sorting - min temp
		locationDetailList.sort(Comparator.comparingDouble(MetaWeatherDetailResponseDTO::getMin_temp));
		Double tempMinima = locationDetailList.get(0).getMin_temp();
		// Info
		est.setTempMaxima(tempMaxima);
		est.setTempMinima(tempMinima);
		return est;
	}
}
