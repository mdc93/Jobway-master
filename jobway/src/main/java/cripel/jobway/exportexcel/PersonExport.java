package cripel.jobway.exportexcel;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import cripel.jobway.model.Event;
import cripel.jobway.model.Formation;
import cripel.jobway.model.PerLan;
import cripel.jobway.model.Person;
import cripel.jobway.model.ResidencePermit;
import cripel.jobway.model.WorkExperience;
import cripel.jobway.model.WorkSector;
import cripel.jobway.utilities.DateUtil;

/**
 * The class to export people's data Linked with the header in
 * {@link PersonExportHeader} Very bad job 
 */
public class PersonExport {
	private LinkedHashMap<Integer, Object> map = new LinkedHashMap<>();
	/** Constant used to remove square bracket from a Collections toString */
	private static final String BRACKETLESS="\\[|\\]";
	
	public PersonExport(Person person, LocalDate begin, LocalDate end) {
		exportTable(person, begin, end);
	}

	/**
	 * Very rough export one by one not scalable better to scrap this
	 *
	 * @param person
	 */
	private void exportTable(Person person, LocalDate begin, LocalDate end) {

		int index = 0;
		String nationalite;
		Set<String> fse;
		// Important Data
		map.put(index++, person.getIdPerson());
		map.put(index++, person.getPersonLastName());
		map.put(index++, person.getPersonFirstName());
		map.put(index++, person.getPersonAddress());
		index = exportCity(person, index);
		map.put(index++, person.getPersonPhone());
		map.put(index++, person.getPersonBirthDate());
		map.put(index++, person.getPersonGender());
		if (person.getCountries() != null) {
			//ABUSE mdr
			nationalite = person.getCountries().toString().replaceAll(BRACKETLESS, "");
			map.put(index++, nationalite);
		} else
			map.put(index++, " ");

		if (person.getCountries() != null) {
			//Faudrait exclure INCONNU, mais c'est pas important maintenant
			fse = person.getCountries().stream()
					.map( type -> type.getCountrytype().getCountryTypeName())
					.collect(Collectors.toSet());

			map.put(index++, fse.toString().replaceAll(BRACKETLESS, ""));
		}
		else
			map.put(index++, " ");
		if (person.getIncometypes() != null) {
			map.put(index++, person.getIncometypes().toString().replaceAll(BRACKETLESS, ""));
		} else
			map.put(index++, " ");
		map.put(index++, person.getPersonForemInsDate());
		map.put(index++, person.getPersonUnemployementDuration());
		map.put(index++, exportFormation(person));
		if (person.getHousehold().getHouseholdNumberAdult() == 0)
			map.put(index++, " ");
		else if ((person.getHousehold().getHouseholdNumberAdult() + person.getHousehold().getHouseholdNumberChildren())==1)
			map.put(index++, "Isolé");
		else
			map.put(index++, "Ménage");

		map.put(index++, person.getHousehold().getHouseholdNumberChildren());
		map.put(index++, exportBoolean(person.getDisability().isDisReco()));
		map.put(index++, person.getDisability().getDisOther());
		map.put(index++, person.getFile().getRegistrationDate());
		map.put(index++, " ");
		map.put(index++, totalHour(person, begin, end));

		// Bonus Data
		map.put(index++, person.getPersonOrientation());
		map.put(index++, person.getPersonOrientationNote());
		map.put(index++, nullCheck(person.getEmployee()));
		map.put(index++, person.getPersonNiss());
		map.put(index++, person.getPersonMail());
		map.put(index++, person.getPersonArrivalDate());
		map.put(index++, nullCheck(person.getCountryByIdCountry()));
		map.put(index++, nullCheck(person.getSituationterritory()));

		if (person.getFamilyreunions() != null)
			map.put(index++, person.getFamilyreunions().toString().replaceAll(BRACKETLESS, ""));
		else
			map.put(index++, " ");

		if (!person.getResidencepermits().isEmpty()) {
			int id = 0;
			ResidencePermit permit = null;
			for (ResidencePermit res : person.getResidencepermits()) {
				if (id < res.getIdResidencePermit()) {
					permit = res;
					id = res.getIdResidencePermit();
				}

			}
			map.put(index++, permit.getResidencepermittype().toString());
			map.put(index++, permit.getResidencePermitAnnex());
			map.put(index++, permit.getResidencePermitIssueDate());
			map.put(index++, permit.getResidencePermitValidityDate());
		} else {
			map.put(index++, " ");
			map.put(index++, " ");
			map.put(index++, " ");
			map.put(index++, " ");
		}
		map.put(index++, exportBoolean(person.isPersonIsBelgian()));
		map.put(index++, nullCheck(person.getCivilstatus()));
		map.put(index++, nullCheck(person.getCountryByIdCountryReunionNationality()));
		map.put(index++, person.getHousehold().getHouseholdNumberChildren());
		map.put(index++, person.getHousehold().getHouseholdNumberAdult());
		map.put(index++, exportBoolean(person.getHousehold().isHouseholdGuardNeeded()));
		map.put(index++, person.getHousehold().getHouseholdIspName());
		map.put(index++, person.getPersonHealthcare());
		if (person.getIncometypes() != null) {
			map.put(index++, person.getIncometypes().toString().replaceAll(BRACKETLESS, ""));
		} else
			map.put(index++, " ");

		map.put(index++, " ");
		map.put(index++, person.getPersonForemInsDate());
		map.put(index++, person.getPersonNotes());

		if (person.getPerDrL() != null && !person.getPerDrL().isEmpty()) {
			map.put(index++, person.getPerDrL().toString().replaceAll(BRACKETLESS, ""));
		} else
			map.put(index++, " ");
		map.put(index++, exportBoolean(person.isPersonHasVehicle()));

		if (person.getPerDrL() != null && !person.getPerDrL().isEmpty())
			map.put(index++, person.getLocomotionmeans().toString().replaceAll(BRACKETLESS, ""));
		else
			map.put(index++, " ");

		index = exportLanguages(index, person);

		map.put(index++, exportBoolean(person.getWorksearch().isSearching()));
		int limit = 0;
		for (WorkSector work : person.getWorksearch().getWorkSectors()) {
			if (limit < 3)
				map.put(index++, work.getWorkSectorName());
			limit++;
		}

		while (limit < 3) {
			map.put(index++, " ");
			limit++;
		}

		map.put(index++, person.getWorksearch().getWorkSearchAnnex());
		map.put(index++, person.getWorksearch().getAvailabilities().toString().replaceAll(BRACKETLESS, ""));
		map.put(index++, person.getWorksearch().getWorkSearchOtherAvailibility());
		map.put(index++, exportBoolean(person.isPersonNewsLetterWork()));
	

		for (WorkExperience exp : person.getWorkexperiences()) {
			map.put(index++, exp.getWorkExpName());
			map.put(index++, exp.getWorkExpDurationMonth());
			map.put(index++, exp.getWorkSector());
			map.put(index++, exportBoolean(exp.isWorkExpBelgium()));
			map.put(index++, nullCheck(exp.getWorkexptype()));
			String[] split = exp.getWorktasks().toString().replaceAll(BRACKETLESS, "").split(",");
			for (int i = 0; i < 3; i++) {
				if (split.length > i)
					map.put(index++, split[i]);
				else
					map.put(index++, " ");
			}
		}
	}

	private int exportCity(Person person, int index) {
		if (person.getCity() != null) {
			map.put(index++, person.getCity().getPostalcode().getPostalCodeNumber());
			map.put(index++, person.getCity().getCityName());
		} else {
			map.put(index++, " ");
			map.put(index++, " ");
		}
		return index;
	}

	private int exportLanguages(int index, Person person) {
		String motherLan = " ";
		String comLan = " ";
		String frenchLevel = " ";
		List<PerLan> otherLanguage = new ArrayList<>(0);
		for (PerLan perlan : person.getPerLans()) {
			if (perlan.isLangCom()) {
				comLan = perlan.getLanguage().getLanguageName();
			}

			if (perlan.getLangLevel().contains("Langue maternelle")) {
				motherLan = perlan.getLanguage().getLanguageName();
			}

			if (perlan.getLanguage().getLanguageName().contains("Français")) {
				frenchLevel = perlan.getLangLevel();
			} else {
				otherLanguage.add(perlan);
			}
		}

		map.put(index++, motherLan);
		map.put(index++, comLan);
		map.put(index++, frenchLevel);
		map.put(index++, nullCheck(person.getFrenchTest()));
		for (int i = 0; i < 2; i++) {
			if (otherLanguage.size() >= i + 1) {
				map.put(index++, otherLanguage.get(i).toString());
				map.put(index++, otherLanguage.get(i).getLangLevel());
			} else {
				map.put(index++, " ");
				map.put(index++, " ");
			}
		}
		return index;
	}

	private String exportBoolean(Boolean bool) {
		String boolString = null;
		if (bool == null) {
			boolString = " ";
		} else if (bool) {
			boolString = "O";
		} else {
			boolString = "N";
		}

		return boolString;
	}

	private String nullCheck(Object value) {
		if (value == null) {
			return " ";
		}
		return value.toString();
	}

	/**
	 * Calculate the total event duration for this person
	 *
	 * @param person
	 * @return
	 */
	private double totalHour(Person person, LocalDate begin, LocalDate end) {
		double total = 0.00;

		if (begin == null || end == null) {
			for (Event event : person.getEvents()) {
				if (event.getEventDuration() != null && event.getEventDate() != null
						&& DateUtil.convertToLocalDate(event.getEventDate()).getYear() != LocalDate.now().getYear()) {
					total += event.getEventDuration();
				}
			}

		} else {
			for (Event event : person.getEvents()) {
				LocalDate eventDate = DateUtil.convertToLocalDate(event.getEventDate());
				if (event.getEventDuration() != null && eventDate != null && eventDate.isBefore(end)
						&& eventDate.isAfter(begin)) {
					
					total += event.getEventDuration();
				}
			}
		}
		return total / 60;
	}

	private String exportFormation(Person person) {

		for (Formation formation : person.getFormations()) {
			if (!formation.getFormationtype().isIsFormation()) {
				return formation.getFormationtype().toString();
			}
		}
		return "";
	}

	/**
	 * @return the map
	 */
	public LinkedHashMap<Integer, Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(LinkedHashMap<Integer, Object> map) {
		this.map = map;
	}

}
