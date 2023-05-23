package com.example.demo.services;

import com.example.demo.models.SmiteHero;

import com.example.demo.models.SmiteObject;
import com.example.demo.repositories.SmiteHeroRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SmiteHeroService {

    private final SmiteHeroRepository smiteHeroRepository;

@Autowired
    public SmiteHeroService(SmiteHeroRepository smiteHeroRepository) {
        this.smiteHeroRepository = smiteHeroRepository;
}

    public boolean checkForDuplicates(String... array)
    {

        Set<String> set = new HashSet<String>();

        for (String e: array)
        {
            if (set.contains(e)) {
                return true;
            }
            if (e != null) {
                set.add(e);
            }
        }
        return false;
    }


    public void enemy5() {
        List<SmiteHero> list = smiteHeroRepository.findAllByMode("ranked", Sort.by(Sort.Direction.ASC, "id"));
     /*   List<SmiteHero> updatedList = new ArrayList<>();*/
        for (int i = 0; i < list.size(); i+=10) {

            System.out.println(i + "/" + list.size());
            String enemy2 = "," + list.get(i).getName() + "," + list.get(i+1).getName() +
                    "," + list.get(i+2).getName() + "," + list.get(i+3).getName() + "," + list.get(i+4).getName() + ",";
            String enemy1 = "," + list.get(i+5).getName() + "," + list.get(i+6).getName() +
                    "," + list.get(i+7).getName() + "," + list.get(i+8).getName() + "," + list.get(i+9).getName() + ",";
            list.get(i).setEnemy5(enemy1);
            list.get(i+1).setEnemy5(enemy1);
            list.get(i+2).setEnemy5(enemy1);
            list.get(i+3).setEnemy5(enemy1);
            list.get(i+4).setEnemy5(enemy1);
            list.get(i+5).setEnemy5(enemy2);
            list.get(i+6).setEnemy5(enemy2);
            list.get(i+7).setEnemy5(enemy2);
            list.get(i+8).setEnemy5(enemy2);
            list.get(i+9).setEnemy5(enemy2);
        }
        smiteHeroRepository.saveAll(list);

    }
    public void replayFinder(String name, String role, String enemy) {
    List<SmiteHero> list = smiteHeroRepository.findAllByMode("ranked", Sort.by(Sort.Direction.ASC, "id"));
        for (SmiteHero smiteHero : list) {
            if (smiteHero.getRole().equals(role) && smiteHero.getName().equals(name) && smiteHero.getMmr()>2000 && smiteHero.getMode().equals("ranked") /*&& smiteHero.getEnemy().equals(enemy)*/) {
                System.out.println("match id = " + smiteHero.getMatchId() + ", mmr= " + smiteHero.getMmr() + ", enemy= " + smiteHero.getEnemy());
            }
        }
    }
    public boolean checkForDuplicatesGlyphs(String... array)
    {

        Set<String> set = new HashSet<String>(List.of(array));


        for (String e: array)
        {

            if (set.contains("Amulet of Silence")&&set.contains("Heartward Amulet")) {
                return true;
            }
            if (set.contains("Amulet of the Stronghold")&&set.contains("Heartward Amulet")) {
                return true;
            }
            if (set.contains("Bancroft's Talon")&&set.contains("Bancroft's Claw")) {
                return true;
            }
            if (set.contains("Bancroft's Talon")&&set.contains("Nimble Bancroft's Talon")) {
                return true;
            }
            if (set.contains("Breastplate of Valor")&&set.contains("Breastplate of Determination")) {
                return true;
            }
            if (set.contains("Breastplate of Valor")&&set.contains("Breastplate of Vigilance")) {
                return true;
            }
            if (set.contains("Rod of Tahuti")&&set.contains("Calamitous Rod of Tahuti")) {
                return true;
            }
            if (set.contains("Rod of Tahuti")&&set.contains("Perfected Rod of Tahuti")) {
                return true;
            }
            if (set.contains("Deathbringer")&&set.contains("Envenomed Deathbringer")) {
                return true;
            }
            if (set.contains("Deathbringer")&&set.contains("Malicious Deathbringer")) {
                return true;
            }
            if (set.contains("Jotunn's Wrath")&&set.contains("Jotunn's Cunning")) {
                return true;
            }
            if (set.contains("Jotunn's Wrath")&&set.contains("Jotunn's Vigor")) {
                return true;
            }
            if (set.contains("Magi's Cloak")&&set.contains("Magi's Revenge")) {
                return true;
            }
            if (set.contains("Magi's Cloak")&&set.contains("Magi's Shelter")) {
                return true;
            }
            if (set.contains("The Executioner")&&set.contains("The Ferocious Executioner")) {
                return true;
            }
            if (set.contains("The Executioner")&&set.contains("The Heavy Executioner")) {
                return true;
            }


            if (e != null) {
                set.add(e);
            }
        }


        return false;
    }
    public boolean checkForGlyphs(String... array)
    {
        String[] glyphs1 = {"Amulet of the Stronghold", "Amulet of Silence", "Bancroft's Claw", "Breastplate of Determination", "Breastplate of Vigilance",
                "Calamitous Rod of Tahuti", "Envenomed Deathbringer", "Jotunn's Cunning", "Jotunn's Vigor", "Magi's Revenge", "Magi's Shelter", "Malicious Deathbringer",
                "Nimble Bancroft's Talon", "Perfected Rod of Tahuti", "The Ferocious Executioner", "The Heavy Executioner"};
        Set<String> glyphs = new HashSet<>(Arrays.asList(glyphs1));
        Set<String> set = new HashSet<>();
        int count = 0;
        Set<String> setTest = new HashSet<>(Arrays.asList(array));
        for (String s : setTest) {
            if (glyphs.stream().anyMatch(s::contains)) {
                count++;
            }
        }
        return count > 1;

    }

    public List<Double> test1(String name, String role, List<SmiteHero> listAll, double mmr, String mode) {
    int wins = 0;
    int losses = 0;

        List<SmiteHero> list = getSmiteHeroes(name, role, listAll, mmr, "1",1);

        for (SmiteHero smiteHero : list) {
            if (smiteHero.isResult()) {
                wins++;
            } else losses++;
        }

        List<Double> doubleList = new ArrayList<>();
        int matches = wins+losses;
        doubleList.add((double) matches);
        doubleList.add((double)(wins*100)/(double) matches);

        return doubleList;
    }

    public Map<String, Double> counters(String name, String role, int number, List<SmiteHero> listAll, boolean check, double mmr) {

        List<SmiteHero> list = getSmiteHeroes(name, role, listAll, mmr, "1",1);
        Map<String, Double> matches = new HashMap<>();
        Map<String, Integer> winners = new HashMap<>();
        Map<String, Integer> losers = new HashMap<>();
        Map<String, Double> winRate = new HashMap<>();
        for (SmiteHero smiteHero : list) {
            if (!matches.containsKey(smiteHero.getEnemy())) {
                matches.put(smiteHero.getEnemy(), 1.0);
            } else {
                matches.put(smiteHero.getEnemy(), matches.get(smiteHero.getEnemy()) + 1.0);
            }
        }

        for (SmiteHero smiteHero : list) {
            if (smiteHero.isResult()) {
                if (!winners.containsKey(smiteHero.getEnemy())) {
                    winners.put(smiteHero.getEnemy(), 1);
                } else {
                    winners.put(smiteHero.getEnemy(), winners.get(smiteHero.getEnemy()) + 1);
                }
            } else {
                if (!losers.containsKey(smiteHero.getEnemy())) {
                    losers.put(smiteHero.getEnemy(), 1);
                } else {
                    losers.put(smiteHero.getEnemy(), losers.get(smiteHero.getEnemy()) + 1);
                }
            }

            matches = matches.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(number)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new
                    ));

        }

        double wins = 0;
        double losses = 0;

        for (Map.Entry<String, Double> entry : matches.entrySet()) {
            try{
                 wins = (double) winners.get(entry.getKey());
            } catch (NullPointerException e) {
                wins = 0;
            }


            try {
                 losses = (double) losers.get(entry.getKey());
            } catch (NullPointerException e){
                losses = 0;
            }

            winRate.put(entry.getKey(), wins/(losses+wins));
        }

        winRate = winRate.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
        if (check) {
            return winRate;
        } else {
            return matches;
        }


    }
    public void parserExcel(double mmr, String mode) throws IOException {

        File file = new File("C:\\Users\\Nikita\\Desktop\\smite.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(1);
        int numberOfGods = 126;
        List<SmiteHero> list = new ArrayList<>();
        if (mode.equals("ranked")) {
            list = smiteHeroRepository.findAllByMode("ranked", Sort.by(Sort.Direction.ASC, "id"));
            sheet = workbook.getSheetAt(0);
        }
        if (mode.equals("normal")) {
            list = smiteHeroRepository.findAllByMode("normal", Sort.by(Sort.Direction.ASC, "id"));
        }

        String name = "";


        for (int i = 1; i < numberOfGods; i++) {
            name = sheet.getRow(i).getCell(0).getStringCellValue();
            List<Double> l1 = test1(name, "Carry", list, mmr, mode);
            List<Double> l2 = test1(name, "Mid", list, mmr, mode);
            List<Double> l3 = test1(name, "Solo", list, mmr, mode);
            List<Double> l4 = test1(name, "Jungle", list, mmr, mode);
            List<Double> l5 = test1(name, "Support", list, mmr, mode);
            double m1 = l1.get(0);
            double m2 = l2.get(0);
            double m3 = l3.get(0);
            double m4 = l4.get(0);
            double m5 = l5.get(0);
            double w1 = l1.get(1);
            double w2 = l2.get(1);
            double w3 = l3.get(1);
            double w4 = l4.get(1);
            double w5 = l5.get(1);

            sheet.getRow(i).getCell(1).setCellValue(m1);
            sheet.getRow(i).getCell(2).setCellValue(m2);
            sheet.getRow(i).getCell(3).setCellValue(m3);
            sheet.getRow(i).getCell(4).setCellValue(m4);
            sheet.getRow(i).getCell(5).setCellValue(m5);
            sheet.getRow(i).getCell(7).setCellValue(w1);
            sheet.getRow(i).getCell(8).setCellValue(w2);
            sheet.getRow(i).getCell(9).setCellValue(w3);
            sheet.getRow(i).getCell(10).setCellValue(w4);
            sheet.getRow(i).getCell(11).setCellValue(w5);
            }
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        inputStream.close();
        outputStream.close();
        System.out.println("Done");
    }

    public void testTest() throws IOException, URISyntaxException {
         String devId = System.getenv("devId");
        String authKey = System.getenv("authKey");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timeStamp = dtf.format(LocalDateTime.now(ZoneOffset.UTC));
        String signature = devId +"createsession"+authKey+timeStamp;
        String hashtext = DigestUtils.md5Hex(signature);
        String url = "https://api.smitegame.com/smiteapi.svc/createsessionJson/"+ devId +"/"+hashtext+"/"+timeStamp;
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JsonNode obj = mapper.readTree(response);
        String session = obj.findValue("session_id").asText();
        signature = devId +"getdataused"+authKey+timeStamp;
        hashtext = DigestUtils.md5Hex(signature);


        String url1 = "https://api.smitegame.com/smiteapi.svc/getdatausedJson/"+ devId +"/"+hashtext+"/"+session+"/"+timeStamp;
        RestTemplate restTemplate1 = new RestTemplate();
        String response1 = restTemplate1.getForObject(url1, String.class);
        ObjectMapper mapper1 = new ObjectMapper();
        JsonNode obj1 = mapper1.readTree(response1);

        System.out.println(obj1);


      /*  HttpResponse httpResponse = callGraphQLService("https://api.stratz.com/graphiql?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJodHRwczovL3N0ZWFtY29tbXVuaXR5LmNvbS9vcGVuaWQvaWQvNzY1NjExOTgxNDQ1MjgyODIiLCJ1bmlxdWVfbmFtZSI6IlRPUk9OVE9KT1BZTyIsIlN1YmplY3QiOiI3ZTg5MjNmZi0wZGVmLTQ1N2EtOGRhMC0yZjNiYWQyM2I3OWEiLCJTdGVhbUlkIjoiMTg0MjYyNTU0IiwibmJmIjoxNjU1MDE1NjM5LCJleHAiOjE2ODY1NTE2MzksImlhdCI6MTY1NTAxNTYzOSwiaXNzIjoiaHR0cHM6Ly9hcGkuc3RyYXR6LmNvbSJ9.VNTGZzOHorcXDEi4zJxdu6ZnmqWd37mBdUina_Jz-6U/", "{match(id: 7057663455) {midLaneOutcome}}");
        ObjectMapper objectMapper = new ObjectMapper();
        String actualResponse = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8.name());
        System.out.println(actualResponse);*/
       /* Response parsedResponse = objectMapper.readValue(actualResponse, Response.class);
        System.out.println(parsedResponse);*/

    }
    public static HttpResponse callGraphQLService(String url, String query)
            throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        URI uri = new URIBuilder(request.getURI())
                .addParameter("query", query)
                .build();
        request.setURI(uri);
        return client.execute(request);
    }
    public List<String> player(String mode) throws JsonProcessingException {
        if (mode.equals("conquest")) {mode="426";}
        if (mode.equals("arena")) {mode="435";}
        if (mode.equals("joust")) {mode="448";}
        if (mode.equals("assault")) {mode="445";}
        if (mode.equals("slash")) {mode="10189";}
        if (mode.equals("ranked")) {mode="451";}


        String devId = System.getenv("devId");;
        String authKey = System.getenv("authKey");;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timeStamp = dtf.format(LocalDateTime.now(ZoneOffset.UTC));
        String signature = devId+"createsession"+authKey+timeStamp;
        String hashtext = DigestUtils.md5Hex(signature);
        String url = "https://api.smitegame.com/smiteapi.svc/createsessionJson/"+devId+"/"+hashtext+"/"+timeStamp;
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JsonNode obj = mapper.readTree(response);
        String session = obj.findValue("session_id").asText();
        signature = devId+"getplayerstatus"+authKey+timeStamp;
        hashtext = DigestUtils.md5Hex(signature);


        String url1 = "https://api.smitegame.com/smiteapi.svc/getplayerstatusJson/"+devId+"/"+hashtext+"/"+session+"/"+timeStamp+"/718506150";
        RestTemplate restTemplate1 = new RestTemplate();
        String response1 = restTemplate1.getForObject(url1, String.class);
        ObjectMapper mapper1 = new ObjectMapper();
        JsonNode obj1 = mapper1.readTree(response1);


        String matchId = obj1.findValue("Match").asText();

        signature = devId+"getmatchplayerdetails"+authKey+timeStamp;
        hashtext = DigestUtils.md5Hex(signature);




        url1 = "https://api.smitegame.com/smiteapi.svc/getmatchplayerdetailsJson/"+devId+"/"+hashtext+"/"+session+"/"+timeStamp+"/"+matchId;
        restTemplate1 = new RestTemplate();
        response1 = restTemplate1.getForObject(url1, String.class);
        mapper1 = new ObjectMapper();
        obj1 = mapper1.readTree(response1);

        List<String> playerIds = obj1.findValuesAsText("playerId");
        List<String> playerGods = obj1.findValuesAsText("GodName");
        List<String> playerNames = obj1.findValuesAsText("playerName");
        List<String> team = obj1.findValuesAsText("taskForce");
         signature = devId+"getqueuestats"+authKey+timeStamp;
        hashtext = DigestUtils.md5Hex(signature);

        String signature2 = devId+"getplayer"+authKey+timeStamp;
        String hashtext2 = DigestUtils.md5Hex(signature2);


        List<String> result = new ArrayList<>();

        for (int i = 0; i < playerIds.size(); i++) {
            if (team.get(i).equals("1")) {
                int wins1 = 0;
                int matches1 = 0;
                int godWins = 0;
                int godMatches = 0;
                int godKills = 0;
                int godAssists = 0;
                int godDeaths = 0;
                String url2 = "https://api.smitegame.com/smiteapi.svc/getplayerJson/"+devId+"/"+hashtext2+"/"+session+"/"+timeStamp+"/"+playerIds.get(i);
                url1 = "https://api.smitegame.com/smiteapi.svc/getqueuestatsJson/"+devId+"/"+hashtext+"/"+session+"/"+timeStamp+"/"+playerIds.get(i)+"/"+mode;
                restTemplate1 = new RestTemplate();
                RestTemplate restTemplate2 = new RestTemplate();
                String response2 = restTemplate2.getForObject(url2, String.class);
                response1 = restTemplate1.getForObject(url1, String.class);
                mapper1 = new ObjectMapper();
                ObjectMapper mapper2 = new ObjectMapper();
                JsonNode obj2 = mapper2.readTree(response2);
                obj1 = mapper1.readTree(response1);



                List<String> wins = obj1.findValuesAsText("Wins");
                List<String> matches = obj1.findValuesAsText("Matches");
                List<String> gods = obj1.findValuesAsText("God");
                List<String> kills = obj1.findValuesAsText("Kills");
                List<String> deaths = obj1.findValuesAsText("Deaths");
                List<String> assists = obj1.findValuesAsText("Assists");
                for (int l = 0 ; l < gods.size(); l++) {
                    if (gods.get(l).equals(playerGods.get(i))) {
                        godWins = Integer.parseInt(wins.get(l));
                        godMatches = Integer.parseInt(matches.get(l));
                        godAssists = Integer.parseInt(assists.get(l));
                        godKills = Integer.parseInt(kills.get(l));
                        godDeaths = Integer.parseInt(deaths.get(l));
                    }
                }
                int kills0 = 0;
                int deaths0 = 0;
                int assists0 = 0;

                for (String deaths1 : deaths) {
                    deaths0 += Integer.parseInt(deaths1);
                }
                for (String kills1 : kills) {
                    kills0 += Integer.parseInt(kills1);
                }
                for (String assists1 : assists) {
                    assists0 += Integer.parseInt(assists1);
                }

                String playerName = playerNames.get(i);
                String godName = playerGods.get(i);

                for (int j = 0; j < wins.size(); j++) {
                    wins1 = wins1 + Integer.parseInt(wins.get(j));
                    matches1 = matches1 + Integer.parseInt(matches.get(j));
                }
                result.add(playerName);
                result.add(godName);
                result.add(String.valueOf((((double) godAssists/2) + godKills) / (double) godDeaths));
                result.add(String.valueOf((double)100*godWins/(double)godMatches));
                result.add(String.valueOf(godMatches));
                result.add(String.valueOf((((double) assists0/2) + kills0) / (double) deaths0));
                result.add(String.valueOf((double)100*wins1 / (double) matches1));
                result.add(String.valueOf(matches1));
                try {
                    result.add(obj2.findValue("HoursPlayed").asText());
                } catch (NullPointerException e) {
                    result.add("0");
                }


            }

        }
        for (int i = 0; i < playerIds.size(); i++) {
            if (team.get(i).equals("2")) {
                int wins1 = 0;
                int matches1 = 0;
                int godWins = 0;
                int godMatches = 0;
                int godKills = 0;
                int godAssists = 0;
                int godDeaths = 0;
                String url2 = "https://api.smitegame.com/smiteapi.svc/getplayerJson/"+devId+"/"+hashtext2+"/"+session+"/"+timeStamp+"/"+playerIds.get(i);
                url1 = "https://api.smitegame.com/smiteapi.svc/getqueuestatsJson/"+devId+"/"+hashtext+"/"+session+"/"+timeStamp+"/"+playerIds.get(i)+"/"+mode;
                restTemplate1 = new RestTemplate();
                RestTemplate restTemplate2 = new RestTemplate();
                String response2 = restTemplate2.getForObject(url2, String.class);
                response1 = restTemplate1.getForObject(url1, String.class);
                mapper1 = new ObjectMapper();
                ObjectMapper mapper2 = new ObjectMapper();
                JsonNode obj2 = mapper2.readTree(response2);
                obj1 = mapper1.readTree(response1);



                List<String> wins = obj1.findValuesAsText("Wins");
                List<String> matches = obj1.findValuesAsText("Matches");
                List<String> gods = obj1.findValuesAsText("God");
                List<String> kills = obj1.findValuesAsText("Kills");
                List<String> deaths = obj1.findValuesAsText("Deaths");
                List<String> assists = obj1.findValuesAsText("Assists");
                for (int l = 0 ; l < gods.size(); l++) {
                    if (gods.get(l).equals(playerGods.get(i))) {
                        godWins = Integer.parseInt(wins.get(l));
                        godMatches = Integer.parseInt(matches.get(l));
                        godAssists = Integer.parseInt(assists.get(l));
                        godKills = Integer.parseInt(kills.get(l));
                        godDeaths = Integer.parseInt(deaths.get(l));
                    }
                }
                int kills0 = 0;
                int deaths0 = 0;
                int assists0 = 0;

                for (String deaths1 : deaths) {
                    deaths0 += Integer.parseInt(deaths1);
                }
                for (String kills1 : kills) {
                    kills0 += Integer.parseInt(kills1);
                }
                for (String assists1 : assists) {
                    assists0 += Integer.parseInt(assists1);
                }

                String playerName = playerNames.get(i);
                String godName = playerGods.get(i);

                for (int j = 0; j < wins.size(); j++) {
                    wins1 = wins1 + Integer.parseInt(wins.get(j));
                    matches1 = matches1 + Integer.parseInt(matches.get(j));
                }
                result.add(playerName);
                result.add(godName);
                result.add(String.valueOf((((double) godAssists/2) + godKills) / (double) godDeaths));
                result.add(String.valueOf((double)100*godWins/(double)godMatches));
                result.add(String.valueOf(godMatches));
                result.add(String.valueOf((((double) assists0/2) + kills0) / (double) deaths0));
                result.add(String.valueOf((double)100*wins1 / (double) matches1));
                result.add(String.valueOf(matches1));
                try {
                    result.add(obj2.findValue("HoursPlayed").asText());
                } catch (NullPointerException e) {
                    result.add("0");
                }

            }


        }

        for (int i=0; i<result.size(); i++) {
            if (result.get(i).equals("NaN")) {
                result.set(i, "0");
            }
        }
        return result;
    }
    public void parser(int date, int hour, String mode) throws JsonProcessingException {
    String modeId="";
        List<SmiteHero> smiteList1 = new ArrayList<>();
        String modeString="";
    if (mode.equals("ranked")) {
        modeId = "451";
        modeString = "Ranked: Conquest";
    } else {
        if (mode.equals("normal")) {
            modeId = "426";
            modeString = "Normal: Conquest";
        } else {
            if (mode.equals("assault")) {
                modeId = "445";
                modeString = "Normal: Assault";
            }
        }
    }

        String devId = System.getenv("devId");;
        String authKey = System.getenv("authKey");;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timeStamp = dtf.format(LocalDateTime.now(ZoneOffset.UTC));
        String signature = devId+"createsession"+authKey+timeStamp;
        String hashtext = DigestUtils.md5Hex(signature);
        String url = "https://api.smitegame.com/smiteapi.svc/createsessionJson/"+devId+"/"+hashtext+"/"+timeStamp;
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JsonNode obj = mapper.readTree(response);
        String session = obj.findValue("session_id").asText();

                signature = devId+"getmatchidsbyqueue"+authKey+timeStamp;
        hashtext = DigestUtils.md5Hex(signature);
                String url1 = "https://api.smitegame.com/smiteapi.svc/getmatchidsbyqueueJson/"+devId+"/"+hashtext+"/"+session+"/"+timeStamp+"/"+modeId+"/"+date+"/"+hour;
        RestTemplate restTemplate1 = new RestTemplate();
        String response1 = restTemplate1.getForObject(url1, String.class);
        ObjectMapper mapper1 = new ObjectMapper();
        JsonNode obj1 = mapper1.readTree(response1);

        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(obj1.toString());
        List<String> matchesAll = new ArrayList<>();
        while (matcher.find())
        {
            matchesAll.add(matcher.group(1));
        }
        List<String> matchesAs10 = new ArrayList<>();
        System.out.println(matchesAll.size());

        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        String s8 = "";
        String s9 = "";
        String s10 = "";
        for (int i = 0; i < matchesAll.size(); i+=10) {
            try{
                 s1 = matchesAll.get(i);
            } catch (Exception ignored) {s1 = "";}
            try{
                s2 =","+matchesAll.get(i+1);
            } catch (Exception ignored) {s2 = "";}
            try{s3 = ","+matchesAll.get(i+2);
            } catch (Exception ignored) {s3 = "";}
            try{s4 = ","+matchesAll.get(i+3);
            } catch (Exception ignored) {s4 = "";}
            try{ s5 = ","+matchesAll.get(i+4);
            } catch (Exception ignored) {s5 = "";}
            try{ s6 = ","+matchesAll.get(i+5);
            } catch (Exception ignored) {s6 = "";}
            try{   s7 = ","+matchesAll.get(i+6);
            } catch (Exception ignored) {s7 = "";}
            try{ s8 = ","+matchesAll.get(i+7);
            } catch (Exception ignored) {s8 = "";}
            try{ s9 = ","+matchesAll.get(i+8);
            } catch (Exception ignored) {s9 = "";}
            try{ s10 = ","+matchesAll.get(i+9);
            } catch (Exception ignored) {s10 = "";}

            matchesAs10.add(s1+s2+s3+s4+s5+s6+s7+s8+s9+s10);
        }

        for (int i = 0; i < matchesAs10.size(); i++) {
            timeStamp = dtf.format(LocalDateTime.now(ZoneOffset.UTC));

            System.out.println(i);

            String matchId = matchesAs10.get(i);

              signature = devId+"getmatchdetailsbatch"+authKey+timeStamp;
            hashtext = DigestUtils.md5Hex(signature);
            String url2 = "https://api.smitegame.com/smiteapi.svc/getmatchdetailsbatchJson/"+devId+"/"+hashtext+"/"+session+"/"+timeStamp+"/"+matchId;
            RestTemplate restTemplate2 = new RestTemplate();
           /*try {*/
            String response2;
            try {
                response2 = restTemplate2.getForObject(url2, String.class);
            } catch (HttpClientErrorException e) {
                 response2 = restTemplate2.getForObject(url2, String.class);
            }
               ObjectMapper mapper2 = new ObjectMapper();
               JsonNode obj2 = mapper2.readTree(response2);

            System.out.println(obj2.size());

            if (obj2.size() % 10 != 0) {
                continue;
            }
            if (obj2.size() == 0) {
                continue;
            }

             if (!obj2.findValue("name").asText().equals(modeString)) {
                timeStamp = dtf.format(LocalDateTime.now(ZoneOffset.UTC));
                 signature = devId+"createsession"+authKey+timeStamp;
                hashtext = DigestUtils.md5Hex(signature);
                  url = "https://api.smitegame.com/smiteapi.svc/createsessionJson/"+devId+"/"+hashtext+"/"+timeStamp;
                 mapper = new ObjectMapper();
                  restTemplate = new RestTemplate();
                 response = restTemplate.getForObject(url, String.class);
                  obj = mapper.readTree(response);
                  session = obj.findValue("session_id").asText();
                  i--;
                  continue;
             }
               SmiteHero smiteHero;

               for (int j = 0; j<obj2.size(); j++) {


                   String name;
                   try {
                       name = obj2.get(j).findValue("Reference_Name").asText();
                   } catch (NullPointerException e) {
                       response2 = restTemplate2.getForObject(url2, String.class);
                        obj2 = mapper2.readTree(response2);
                       name = obj2.get(j).findValue("Reference_Name").asText();
                   }

                   String role = obj2.get(j).findValue("Role").asText();
                   String ss1 = obj2.get(j).findValue("Item_Purch_1").asText();
                   String ss2 = obj2.get(j).findValue("Item_Purch_2").asText();
                   String ss3 = obj2.get(j).findValue("Item_Purch_3").asText();
                   String ss4 = obj2.get(j).findValue("Item_Purch_4").asText();
                   String ss5 = obj2.get(j).findValue("Item_Purch_5").asText();
                   String ss6 = obj2.get(j).findValue("Item_Purch_6").asText();
                   String ss7 = obj2.get(j).findValue("Item_Active_1").asText();
                   String ss8 = obj2.get(j).findValue("Item_Active_2").asText();
                   Double mmr = obj2.get(j).findValue("Rank_Stat_Conquest").asDouble();
                   int matchId1 = obj2.get(j).findValue("Match").asInt();

                   boolean result;
                   String enemy = "";
                   String enemy5 = "";
                   result= obj2.get(j).findValue("Win_Status").asText().equals("Winner");
                   List<String> enemyNames;
                   List<String> enemyNames2 = new ArrayList<>();
                   if (mode.equals("assault")) {
                      enemyNames = obj2.findValuesAsText("Reference_Name");
                       for (int p = 0; p < enemyNames.size(); p+=5) {
                           String s = "," + enemyNames.get(p) + "," + enemyNames.get(p+1) + "," + enemyNames.get(p+2) + "," + enemyNames.get(p+3) + "," + enemyNames.get(p+4) + ",";
                           enemyNames2.add(s);
                       }
                       if ((j / 5) % 2 == 0) {
                           enemy = enemyNames2.get((j / 5) + 1);
                       } else enemy = enemyNames2.get((j / 5) - 1);


                   } else {
                       for (int n = 10*(j/10); n < 10*(j/10)+10; n++) {

                           if (obj2.get(n).findValue("Role").asText().equals(role) && !obj2.get(n).findValue("Reference_Name").asText().equals(name)) {
                               enemy = obj2.get(n).findValue("Reference_Name").asText();
                               break;
                           }

                       }
                       enemyNames = obj2.findValuesAsText("Reference_Name");
                       for (int p = 0; p < enemyNames.size(); p+=5) {
                           String s = "," + enemyNames.get(p) + "," + enemyNames.get(p+1) + "," + enemyNames.get(p+2) + "," + enemyNames.get(p+3) + "," + enemyNames.get(p+4) + ",";
                           enemyNames2.add(s);
                       }
                       if ((j / 5) % 2 == 0) {
                           enemy5 = enemyNames2.get((j / 5) + 1);
                       } else enemy5 = enemyNames2.get((j / 5) - 1);
                   }

                   if (mode.equals("ranked")) {
                       smiteHero = new SmiteHero(name, role, ss1, ss2, ss3, ss4, ss5, ss6, ss7, ss8, result, enemy, mmr, matchId1, "ranked", enemy5);

                       smiteList1.add(smiteHero);
                   }
                   if (mode.equals("normal")) {
                       smiteHero = new SmiteHero(name, role, ss1, ss2, ss3, ss4, ss5, ss6, ss7, ss8, result, enemy, mmr, matchId1, "normal", enemy5);

                       smiteList1.add(smiteHero);
                   }
                   if (mode.equals("assault")) {
                       smiteHero = new SmiteHero(name, role, ss1, ss2, ss3, ss4, ss5, ss6, ss7, ss8, result, enemy, mmr, matchId1, "assault", "");

                       smiteList1.add(smiteHero);
                   }



               }

                smiteHeroRepository.saveAll(smiteList1);



            System.out.println(i+"/"+matchesAs10.size());
           /*}catch (Exception ignored) {}*/


        }

        System.out.println("---------------------------------------------------------------");
    }

    public Map<String, Double> getItem1(List<SmiteHero> list1) {



        System.out.println(list1.size());
        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        Map<String, Double> calc = new HashMap<>();

        for (SmiteHero smiteHero : list1) {
            if (!smiteHero.isResult()) {
                if (losses.containsKey(smiteHero.getS1())) {
                    losses.put(smiteHero.getS1(), losses.get(smiteHero.getS1()) + 1);
                } else {
                    losses.put(smiteHero.getS1(), 1);
                }
            } else {
                if (wins.containsKey(smiteHero.getS1())) {
                    wins.put(smiteHero.getS1(), wins.get(smiteHero.getS1()) + 1);
                } else {
                    wins.put(smiteHero.getS1(), 1);
                }
            }
        }


        String item1 = "";
        Map<String, Double> calc1 = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            if (losses.containsKey(entry.getKey())){
                if ((double) (wins.get(entry.getKey())+losses.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            } else {
                if ((double) (wins.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            }

            int pos = entry.getValue();
            int negative = 0;
            try {
                 negative = losses.get(entry.getKey());
            } catch (Exception ignored) {

            }
            Double value =((pos+1.9208)/(pos+negative)-1.96 * Math.sqrt((double) (pos*negative)/(pos+negative)+0.9604)/(pos+negative))/(1+3.8416/(pos+negative));
            calc.put(entry.getKey(), value);
            item1 = item1 + entry.getKey()+ "|" + pos + "/" + negative + ",";
        }
        calc.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry1 -> calc1.put(entry1.getKey(), entry1.getValue()));
        calc1.put(item1, 0.00);
        return calc1;
    }

    public List<SmiteHero> getSmiteHeroes(String name, String role, List<SmiteHero> list, double mmr, String enemy, int checker) {
    if (checker == 1) {
        if (enemy.equals("1")) {
            if (mmr==1) {
                List<SmiteHero> list1 = new ArrayList<>();
                for (SmiteHero smiteHero :  list) {
                    if (smiteHero.getRole().equals(role) && smiteHero.getName().equals(name) && smiteHero.getMode().equals("normal") ) {
                        list1.add(smiteHero);
                    }
                }
                return list1;
            } else {
                if (mmr == 0) {
                    List<SmiteHero> list1 = new ArrayList<>();
                    for (SmiteHero smiteHero :  list) {
                        if (smiteHero.getName().equals(name) && smiteHero.getMode().equals("assault") ) {
                            list1.add(smiteHero);
                        }
                    }
                    return list1;
                } else {
                    List<SmiteHero> list1 = new ArrayList<>();
                    for (SmiteHero smiteHero :  list) {
                        if (smiteHero.getMode().equals("ranked") && smiteHero.getRole().equals(role) && smiteHero.getName().equals(name) && smiteHero.getMmr()>mmr && smiteHero.getMmr()<mmr+500.0) {
                            list1.add(smiteHero);
                        }
                    }
                    return list1;
                }
            }
        } else {
            if (mmr==1) {
                List<SmiteHero> list1 = new ArrayList<>();
                for (SmiteHero smiteHero :  list) {
                    if (smiteHero.getRole().equals(role) && smiteHero.getName().equals(name) && smiteHero.getMode().equals("normal") && smiteHero.getEnemy().equals(enemy)) {
                        list1.add(smiteHero);
                    }
                }
                return list1;
            } else {
                if (mmr == 0) {
                    List<SmiteHero> list1 = new ArrayList<>();
                    for (SmiteHero smiteHero :  list) {
                        if (smiteHero.getName().equals(name) && smiteHero.getMode().equals("assault") && smiteHero.getEnemy().contains(enemy)) {
                            list1.add(smiteHero);
                        }
                    }
                    return list1;
                } else {
                    List<SmiteHero> list1 = new ArrayList<>();
                    for (SmiteHero smiteHero :  list) {
                        if (smiteHero.getMode().equals("ranked") && smiteHero.getRole().equals(role) && smiteHero.getName().equals(name) && smiteHero.getMmr()>mmr && smiteHero.getMmr()<mmr+500.0 && smiteHero.getEnemy().equals(enemy)) {
                            list1.add(smiteHero);
                        }
                    }
                    return list1;
                }
            }
        }
    } else {
        if (mmr==1) {
            List<SmiteHero> list1 = new ArrayList<>();
            for (SmiteHero smiteHero :  list) {
                if (smiteHero.getRole().equals(role) && smiteHero.getName().equals(name) && smiteHero.getMode().equals("normal") && smiteHero.getEnemy5().contains("," + enemy + ",")) {
                    list1.add(smiteHero);
                }
            }
            return list1;
        } else
             {
                List<SmiteHero> list1 = new ArrayList<>();
                for (SmiteHero smiteHero :  list) {
                    if (smiteHero.getMode().equals("ranked") && smiteHero.getRole().equals(role) && smiteHero.getName().equals(name) && smiteHero.getMmr()>mmr && smiteHero.getMmr()<mmr+500.0 && smiteHero.getEnemy5().contains("," + enemy + ",")) {
                        list1.add(smiteHero);
                    }
                }
                return list1;
            }

    }




    }


    public List<SmiteHero> getSmiteHeroes1(String name, String role, String enemy/*, double mmr*/) {
        List<SmiteHero> list = smiteHeroRepository.findAll();
        List<SmiteHero> list1 = new ArrayList<>();
        for (SmiteHero smiteHero : list) {
            if (smiteHero.getEnemy() == null) {
                continue;
            }
            if (smiteHero.getRole().equals(role) && smiteHero.getName().equals(name) && smiteHero.getEnemy().equals(enemy)/* && smiteHero.getMmr()>mmr-150.0 && smiteHero.getMmr()<mmr+150.0*/) {
                list1.add(smiteHero);
            }
        }
        return list1;
    }
    public Map<String, Double> getItem2(List<SmiteHero> list1) {



        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        Map<String, Double> calc = new HashMap<>();

        for (int i = 0; i<list1.size(); i++) {
            if (!list1.get(i).isResult()){
                if (losses.containsKey(list1.get(i).getS2())) {
                    losses.put(list1.get(i).getS2(), losses.get(list1.get(i).getS2()) + 1);
                } else {
                    losses.put(list1.get(i).getS2(), 1);
                }
            } else {
                if (wins.containsKey(list1.get(i).getS2())) {
                    wins.put(list1.get(i).getS2(), wins.get(list1.get(i).getS2()) + 1);
                } else {
                    wins.put(list1.get(i).getS2(), 1);
                }
            }
        }

        String item1 = "";
        Map<String, Double> calc1 = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            if (losses.containsKey(entry.getKey())){
                if ((double) (wins.get(entry.getKey())+losses.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            } else {
                if ((double) (wins.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            }
            int pos = entry.getValue();
            int negative = 0;
            try {
                negative = losses.get(entry.getKey());
            } catch (Exception ignored) {

            }
            Double value =((pos+1.9208)/(pos+negative)-1.96 * Math.sqrt((double) (pos*negative)/(pos+negative)+0.9604)/(pos+negative))/(1+3.8416/(pos+negative));
            calc.put(entry.getKey(), value);
            item1 = item1 + entry.getKey()+ "|" + pos + "/" + negative + ",";

        }
        calc.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry1 -> calc1.put(entry1.getKey(), entry1.getValue()));
        calc1.put(item1, 0.00);
        return calc1;
    }
    public Map<String, Double> getItem3(List<SmiteHero> list1) {

        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        Map<String, Double> calc = new HashMap<>();

        for (int i = 0; i<list1.size(); i++) {
            if (!list1.get(i).isResult()){
                if (losses.containsKey(list1.get(i).getS3())) {
                    losses.put(list1.get(i).getS3(), losses.get(list1.get(i).getS3()) + 1);
                } else {
                    losses.put(list1.get(i).getS3(), 1);
                }
            } else {
                if (wins.containsKey(list1.get(i).getS3())) {
                    wins.put(list1.get(i).getS3(), wins.get(list1.get(i).getS3()) + 1);
                } else {
                    wins.put(list1.get(i).getS3(), 1);
                }
            }
        }

        String item1 = "";
        Map<String, Double> calc1 = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            if (losses.containsKey(entry.getKey())){
                if ((double) (wins.get(entry.getKey())+losses.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            } else {
                if ((double) (wins.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            }
            int pos = entry.getValue();
            int negative = 0;
            try {
                negative = losses.get(entry.getKey());
            } catch (Exception ignored) {

            }
            Double value =((pos+1.9208)/(pos+negative)-1.96 * Math.sqrt((double) (pos*negative)/(pos+negative)+0.9604)/(pos+negative))/(1+3.8416/(pos+negative));
            calc.put(entry.getKey(), value);
            item1 = item1 + entry.getKey()+ "|" + pos + "/" + negative + ",";

        }
        calc.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry1 -> calc1.put(entry1.getKey(), entry1.getValue()));
        calc1.put(item1, 0.00);
        return calc1;
    }
    public Map<String, Double> getItem4(List<SmiteHero> list1) {

        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        Map<String, Double> calc = new HashMap<>();

        for (SmiteHero smiteHero : list1) {
            if (!smiteHero.isResult()) {
                if (losses.containsKey(smiteHero.getS4())) {
                    losses.put(smiteHero.getS4(), losses.get(smiteHero.getS4()) + 1);
                } else {
                    losses.put(smiteHero.getS4(), 1);
                }
            } else {
                if (wins.containsKey(smiteHero.getS4())) {
                    wins.put(smiteHero.getS4(), wins.get(smiteHero.getS4()) + 1);
                } else {
                    wins.put(smiteHero.getS4(), 1);
                }
            }
        }

        String item1 = "";
        Map<String, Double> calc1 = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {

                if (losses.containsKey(entry.getKey())){
                    if ((double) (wins.get(entry.getKey())+losses.get(entry.getKey()))/(double)list1.size() < 0.01) {
                        continue;
                    }
                } else {
                    if ((double) (wins.get(entry.getKey()))/(double)list1.size() < 0.01) {
                        continue;
                    }
                }


            int pos = entry.getValue();
            int negative = 0;
            try {
                negative = losses.get(entry.getKey());
            } catch (Exception ignored) {

            }
            Double value =((pos+1.9208)/(pos+negative)-1.96 * Math.sqrt((double) (pos*negative)/(pos+negative)+0.9604)/(pos+negative))/(1+3.8416/(pos+negative));
            calc.put(entry.getKey(), value);
            item1 = item1 + entry.getKey()+ "|" + pos + "/" + negative + ",";

        }
        calc.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry1 -> calc1.put(entry1.getKey(), entry1.getValue()));
        calc1.put(item1, 0.00);
        return calc1;
    }
    public Map<String, Double> getItem5(List<SmiteHero> list1) {

        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        Map<String, Double> calc = new HashMap<>();

        for (SmiteHero smiteHero : list1) {
            if (!smiteHero.isResult()) {
                if (losses.containsKey(smiteHero.getS5())) {
                    losses.put(smiteHero.getS5(), losses.get(smiteHero.getS5()) + 1);
                } else {
                    losses.put(smiteHero.getS5(), 1);
                }
            } else {
                if (wins.containsKey(smiteHero.getS5())) {
                    wins.put(smiteHero.getS5(), wins.get(smiteHero.getS5()) + 1);
                } else {
                    wins.put(smiteHero.getS5(), 1);
                }
            }
        }

        String item1 = "";
        Map<String, Double> calc1 = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            if (losses.containsKey(entry.getKey())){
                if ((double) (wins.get(entry.getKey())+losses.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            } else {
                if ((double) (wins.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            }
            int pos = entry.getValue();
            int negative = 0;
            try {
                negative = losses.get(entry.getKey());
            } catch (Exception ignored) {

            }
            Double value =((pos+1.9208)/(pos+negative)-1.96 * Math.sqrt((double) (pos*negative)/(pos+negative)+0.9604)/(pos+negative))/(1+3.8416/(pos+negative));
            calc.put(entry.getKey(), value);
            item1 = item1 + entry.getKey()+ "|" + pos + "/" + negative + ",";

        }
        calc.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry1 -> calc1.put(entry1.getKey(), entry1.getValue()));
        calc1.put(item1, 0.00);
        return calc1;
    }
    public Map<String, Double> getItem6(List<SmiteHero> list1) {

        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        Map<String, Double> calc = new HashMap<>();

        for (SmiteHero smiteHero : list1) {
            if (!smiteHero.isResult()) {
                if (losses.containsKey(smiteHero.getS6())) {
                    losses.put(smiteHero.getS6(), losses.get(smiteHero.getS6()) + 1);
                } else {
                    losses.put(smiteHero.getS6(), 1);
                }
            } else {
                if (wins.containsKey(smiteHero.getS6())) {
                    wins.put(smiteHero.getS6(), wins.get(smiteHero.getS6()) + 1);
                } else {
                    wins.put(smiteHero.getS6(), 1);
                }
            }
        }

        String item1 = "";
        Map<String, Double> calc1 = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            if (losses.containsKey(entry.getKey())){
                if ((double) (wins.get(entry.getKey())+losses.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            } else {
                if ((double) (wins.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            }
            int pos = entry.getValue();
            int negative = 0;
            try {
                negative = losses.get(entry.getKey());
            } catch (Exception ignored) {

            }
            Double value =((pos+1.9208)/(pos+negative)-1.96 * Math.sqrt((double) (pos*negative)/(pos+negative)+0.9604)/(pos+negative))/(1+3.8416/(pos+negative));
            calc.put(entry.getKey(), value);
            item1 = item1 + entry.getKey()+ "|" + pos + "/" + negative + ",";

        }
        calc.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry1 -> calc1.put(entry1.getKey(), entry1.getValue()));
        calc1.put(item1, 0.00);
        return calc1;
    }
    public Map<String, Double> getItem78(List<SmiteHero> list1) {

        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        Map<String, Double> calc = new HashMap<>();

        for (SmiteHero smiteHero : list1) {
            if (!smiteHero.isResult()) {
                if (losses.containsKey(smiteHero.getS7())) {
                    losses.put(smiteHero.getS7(), losses.get(smiteHero.getS7()) + 1);
                } else {
                    losses.put(smiteHero.getS7(), 1);
                }
            } else {
                if (wins.containsKey(smiteHero.getS7())) {
                    wins.put(smiteHero.getS7(), wins.get(smiteHero.getS7()) + 1);
                } else {
                    wins.put(smiteHero.getS7(), 1);
                }
            }
            if (!smiteHero.isResult()) {
                if (losses.containsKey(smiteHero.getS8())) {
                    losses.put(smiteHero.getS8(), losses.get(smiteHero.getS8()) + 1);
                } else {
                    losses.put(smiteHero.getS8(), 1);
                }
            } else {
                if (wins.containsKey(smiteHero.getS8())) {
                    wins.put(smiteHero.getS8(), wins.get(smiteHero.getS8()) + 1);
                } else {
                    wins.put(smiteHero.getS8(), 1);
                }
            }
        }

        String item1 = "";
        Map<String, Double> calc1 = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            if (losses.containsKey(entry.getKey())){
                if ((double) (wins.get(entry.getKey())+losses.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            } else {
                if ((double) (wins.get(entry.getKey()))/(double)list1.size() < 0.01) {
                    continue;
                }
            }
            int pos = entry.getValue();
            int negative = 0;
            try {
                negative = losses.get(entry.getKey());
            } catch (Exception ignored) {

            }
            Double value =((pos+1.9208)/(pos+negative)-1.96 * Math.sqrt((double) (pos*negative)/(pos+negative)+0.9604)/(pos+negative))/(1+3.8416/(pos+negative));
            calc.put(entry.getKey(), value);
            item1 = item1 + entry.getKey()+ "|" + pos + "/" + negative + ",";

        }
        calc.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry1 -> calc1.put(entry1.getKey(), entry1.getValue()));
        calc1.put(item1, 0.00);
        return calc1;
    }

    public Map<String, Double> draftMethod(String enemy1, String enemy2, String enemy3, String enemy4, String enemy5, String role, int mmr) {
        List<SmiteHero> list = smiteHeroRepository.findAllByMode("ranked", null);
        list.removeIf(n -> (n.getMmr()<=mmr && n.getMmr() < mmr +500));

        List<SmiteHero> list1 = new ArrayList<>();
        List<SmiteHero> list2 = new ArrayList<>();
        List<SmiteHero> list3 = new ArrayList<>();
        List<SmiteHero> list4 = new ArrayList<>();
        List<SmiteHero> list5 = new ArrayList<>();
        for (SmiteHero smiteHero : list) {
            if (smiteHero.getEnemy5().contains("," + enemy1 + ",")) list1.add(smiteHero);
            if (smiteHero.getEnemy5().contains("," + enemy2 + ",")) list2.add(smiteHero);
            if (smiteHero.getEnemy5().contains("," + enemy3 + ",")) list3.add(smiteHero);
            if (smiteHero.getEnemy5().contains("," + enemy4 + ",")) list4.add(smiteHero);
            if (smiteHero.getEnemy5().contains("," + enemy5 + ",")) list5.add(smiteHero);
        }

        Map<String, Double> map =  mapCreator(list);

        Map<String, Double> map1 = mapCreator(list1);
        Map<String, Double> map2 = mapCreator(list2);
        Map<String, Double> map3 = mapCreator(list3);
        Map<String, Double> map4 = mapCreator(list4);
        Map<String, Double> map5 = mapCreator(list5);
        Map<String, Double> map6 = new HashMap<>();


        double v = 0.0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (map1.containsKey(entry.getKey())) {
                v = map1.get(entry.getKey()) - entry.getValue() + map.get(enemy1) - 0.5;
                map6.put(entry.getKey(), v*100);
            }
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (map2.containsKey(entry.getKey())) {
                v = map2.get(entry.getKey()) - entry.getValue() + map.get(enemy2) - 0.5;
                try {map6.put(entry.getKey(), map6.get(entry.getKey())+v*100);} catch (NullPointerException e) {map6.put(entry.getKey(), 0.0);}
            }
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (map3.containsKey(entry.getKey())) {
                v = map3.get(entry.getKey()) - entry.getValue() + map.get(enemy3) - 0.5;
                map6.put(entry.getKey(), map6.get(entry.getKey())+v*100);
            }
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (map4.containsKey(entry.getKey())) {
                v = map4.get(entry.getKey()) - entry.getValue() + map.get(enemy4) - 0.5;
                map6.put(entry.getKey(), map6.get(entry.getKey())+v*100);
            }
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (map5.containsKey(entry.getKey())) {
                v = map5.get(entry.getKey()) - entry.getValue() + map.get(enemy5) - 0.5;
                map6.put(entry.getKey(), map6.get(entry.getKey())+v*100);
            }
        }

//добавить формы + юзеров
        String[] carry = {"Artemis", "Izanami", "Rama", "Ah Muzen Cab", "Danzaburou", "Xbalanque"};
        String[] mid = {"Anubis", "Chang'e", "Ah Muzen Cab"};
        String[] solo = {"Guan Yu", "Chang'e", "Cliodhna"};
        String[] jungle = {""};
        String[] support = {"Geb", "Baron Samedi"};
        Map<String, Double> map7 = new HashMap<>();
        if (role.equals("Carry")) {
            for (Map.Entry<String, Double> entry : map6.entrySet()) {
                if (Arrays.asList(carry).contains(entry.getKey())) {
                    map7.put(entry.getKey(), map6.get(entry.getKey()));
                }
            }
        }
        if (role.equals("Mid")) {
            for (Map.Entry<String, Double> entry : map6.entrySet()) {
                if (Arrays.asList(mid).contains(entry.getKey())) {
                    map7.put(entry.getKey(), map6.get(entry.getKey()));
                }
            }
        }
        if (role.equals("Solo")) {
            for (Map.Entry<String, Double> entry : map6.entrySet()) {
                if (Arrays.asList(solo).contains(entry.getKey())) {
                    map7.put(entry.getKey(), map6.get(entry.getKey()));
                }
            }
        }
        if (role.equals("Jungle")) {
            for (Map.Entry<String, Double> entry : map6.entrySet()) {
                if (Arrays.asList(jungle).contains(entry.getKey())) {
                    map7.put(entry.getKey(), map6.get(entry.getKey()));
                }
            }
        }
        if (role.equals("Support")) {
            for (Map.Entry<String, Double> entry : map6.entrySet()) {
                if (Arrays.asList(support).contains(entry.getKey())) {
                    map7.put(entry.getKey(), map6.get(entry.getKey()));
                }
            }
        }
        return map7;
    }

    private Map<String, Double> mapCreator(List<SmiteHero> list) {
        Map<String, Double> winRates = new HashMap<>();
        Map<String, Integer> wins = new HashMap<>();
        Map<String, Integer> losses = new HashMap<>();
        for (SmiteHero smiteHero : list) {
            if (!wins.containsKey(smiteHero.getName()) || !losses.containsKey(smiteHero.getName())) {
                if (smiteHero.isResult()) {
                    wins.put(smiteHero.getName(), 1);
                } else losses.put(smiteHero.getName(), 1);
            } else {
                if (smiteHero.isResult() && losses.containsKey(smiteHero.getName())) {
                    wins.put(smiteHero.getName(), wins.get(smiteHero.getName())+1);
                } else try{
                    losses.put(smiteHero.getName(), losses.get(smiteHero.getName())+1);
                } catch (NullPointerException ignored) {
                }
            }
        }

        for (Map.Entry<String, Integer> entry : wins.entrySet()) {
            try {
                winRates.put(entry.getKey(),  ((double) entry.getValue()/ (double)(entry.getValue() + losses.get(entry.getKey()))));
            } catch (NullPointerException ignored) {

            }

        }
        return winRates;
    }

    public Double[] calculationsForPlayersStats(List<String> list) {
        double positive1 = Double.parseDouble(list.get(6))*Integer.parseInt(list.get(7))/100+
                Double.parseDouble(list.get(15))*Integer.parseInt(list.get(16))/100+
                Double.parseDouble(list.get(24))*Integer.parseInt(list.get(25))/100+
                Double.parseDouble(list.get(33))*Integer.parseInt(list.get(34))/100+
                Double.parseDouble(list.get(42))*Integer.parseInt(list.get(43))/100;
        double negative1 = Integer.parseInt(list.get(7))- Double.parseDouble(list.get(6))*Integer.parseInt(list.get(7))/100+
                Integer.parseInt(list.get(16))- Double.parseDouble(list.get(15))*Integer.parseInt(list.get(16))/100+
                Integer.parseInt(list.get(25))-Double.parseDouble(list.get(24))*Integer.parseInt(list.get(25))/100+
                Integer.parseInt(list.get(34))-Double.parseDouble(list.get(33))*Integer.parseInt(list.get(34))/100+
                Integer.parseInt(list.get(43))-Double.parseDouble(list.get(42))*Integer.parseInt(list.get(43))/100;
        double positive2 = Double.parseDouble(list.get(51))*Integer.parseInt(list.get(52))/100+
                Double.parseDouble(list.get(60))*Integer.parseInt(list.get(61))/100+
                Double.parseDouble(list.get(69))*Integer.parseInt(list.get(70))/100+
                Double.parseDouble(list.get(78))*Integer.parseInt(list.get(79))/100+
                Double.parseDouble(list.get(87))*Integer.parseInt(list.get(88))/100;
        double negative2 = Integer.parseInt(list.get(52))- Double.parseDouble(list.get(51))*Integer.parseInt(list.get(52))/100+
                Integer.parseInt(list.get(61))- Double.parseDouble(list.get(60))*Integer.parseInt(list.get(61))/100+
                Integer.parseInt(list.get(70))-Double.parseDouble(list.get(69))*Integer.parseInt(list.get(70))/100+
                Integer.parseInt(list.get(79))-Double.parseDouble(list.get(78))*Integer.parseInt(list.get(79))/100+
                Integer.parseInt(list.get(88))-Double.parseDouble(list.get(87))*Integer.parseInt(list.get(88))/100;
        double value1 =((positive1+1.9208)/(positive1+negative1)-1.96 * Math.sqrt((positive1*negative1) /(positive1+negative1)+0.9604)/(positive1+negative1))/(1+3.8416/(positive1+negative1));
        double value2 =((positive2+1.9208)/(positive2+negative2)-1.96 * Math.sqrt((positive2*negative2) /(positive2+negative2)+0.9604)/(positive2+negative2))/(1+3.8416/(positive2+negative2));
        return new Double[]{value1, value2};
    }

    public SmiteObject builderCalculation(String name, String role, double mmr, String enemy, String enemy1, String enemy2, String enemy3, String enemy4) {
        List<SmiteHero> list;
        if (enemy.equals("1")) {
            if (mmr == 1) {
                list = smiteHeroRepository.findAllByMode("normal", Sort.by(Sort.Direction.ASC, "id"));
            } else {
                if (mmr == 0)  {
                    list = smiteHeroRepository.findAllByMode("assault", Sort.by(Sort.Direction.ASC, "id"));
                } else list = smiteHeroRepository.findAllByMode("ranked", Sort.by(Sort.Direction.ASC, "id"));
            }
        } else if (enemy1.isEmpty()){
            if (mmr == 1) list = smiteHeroRepository.findAllByModeAndEnemy("normal", enemy); else {
                if (mmr == 0)  {
                    list = smiteHeroRepository.findAllByMode("assault", Sort.by(Sort.Direction.ASC, "id"));
                } else  list = smiteHeroRepository.findAllByModeAndEnemy("ranked", enemy);
            }
        } else {
            if (mmr == 1) {
                list = smiteHeroRepository.findAllByMode("normal", Sort.by(Sort.Direction.ASC, "id"));
            } else {
                if (mmr == 0)  {
                    list = smiteHeroRepository.findAllByMode("assault", Sort.by(Sort.Direction.ASC, "id"));
                } else list = smiteHeroRepository.findAllByMode("ranked", Sort.by(Sort.Direction.ASC, "id"));
            }
        }
        List<SmiteHero> list1;
        List<SmiteHero> list2 = new ArrayList<>();
        List<SmiteHero> list3= new ArrayList<>();
        List<SmiteHero> list4= new ArrayList<>();
        List<SmiteHero> list5= new ArrayList<>();
        if (!enemy2.isEmpty() && mmr!=0) {
            list1 = getSmiteHeroes(name, role, list, mmr, enemy, 0);
            if (!enemy1.isEmpty()) {
                list2 = getSmiteHeroes(name, role, list, mmr, enemy1,0);
                list3 = getSmiteHeroes(name, role, list, mmr, enemy2,0);
                list4 = getSmiteHeroes(name, role, list, mmr, enemy3,0);
                list5 = getSmiteHeroes(name, role, list, mmr, enemy4,0);
            }
        } else {
            list1 = getSmiteHeroes(name, role, list, mmr, enemy,1);
            if (!enemy1.isEmpty()) {
                list2 = getSmiteHeroes(name, role, list, mmr, enemy1,1);
                list3 = getSmiteHeroes(name, role, list, mmr, enemy2,1);
                list4 = getSmiteHeroes(name, role, list, mmr, enemy3,1);
                list5 = getSmiteHeroes(name, role, list, mmr, enemy4,1);
            }
        }

        Map<String, Double> map1 = getItem1(list1);
        Map<String, Double> map12 = getItem1(list2);
        Map<String, Double> map13 = getItem1(list3);
        Map<String, Double> map14 = getItem1(list4);
        Map<String, Double> map15 = getItem1(list5);
        map12.forEach((k, v) -> map1.merge(k, v, Double::sum));
        map13.forEach((k, v) -> map1.merge(k, v, Double::sum));
        map14.forEach((k, v) -> map1.merge(k, v, Double::sum));
        map15.forEach((k, v) -> map1.merge(k, v, Double::sum));

        Map<String, Double> map2 = getItem2(list1);
        Map<String, Double> map22 = getItem2(list2);
        Map<String, Double> map23 = getItem2(list3);
        Map<String, Double> map24 = getItem2(list4);
        Map<String, Double> map25 = getItem2(list5);
        map22.forEach((k, v) -> map2.merge(k, v, Double::sum));
        map23.forEach((k, v) -> map2.merge(k, v, Double::sum));
        map24.forEach((k, v) -> map2.merge(k, v, Double::sum));
        map25.forEach((k, v) -> map2.merge(k, v, Double::sum));
        Map<String, Double> map3 = getItem3(list1);
        Map<String, Double> map32 = getItem3(list2);
        Map<String, Double> map33 = getItem3(list3);
        Map<String, Double> map34 = getItem3(list4);
        Map<String, Double> map35 = getItem3(list5);
        map32.forEach((k, v) -> map3.merge(k, v, Double::sum));
        map33.forEach((k, v) -> map3.merge(k, v, Double::sum));
        map34.forEach((k, v) -> map3.merge(k, v, Double::sum));
        map35.forEach((k, v) -> map3.merge(k, v, Double::sum));
        Map<String, Double> map4 = getItem4(list1);
        Map<String, Double> map42 = getItem4(list2);
        Map<String, Double> map43 = getItem4(list3);
        Map<String, Double> map44 = getItem4(list4);
        Map<String, Double> map45 = getItem4(list5);
        map42.forEach((k, v) -> map4.merge(k, v, Double::sum));
        map43.forEach((k, v) -> map4.merge(k, v, Double::sum));
        map44.forEach((k, v) -> map4.merge(k, v, Double::sum));
        map45.forEach((k, v) -> map4.merge(k, v, Double::sum));
        Map<String, Double> map5 = getItem5(list1);
        Map<String, Double> map52 = getItem5(list2);
        Map<String, Double> map53 = getItem5(list3);
        Map<String, Double> map54 = getItem5(list4);
        Map<String, Double> map55 = getItem5(list5);
        map52.forEach((k, v) -> map5.merge(k, v, Double::sum));
        map53.forEach((k, v) -> map5.merge(k, v, Double::sum));
        map54.forEach((k, v) -> map5.merge(k, v, Double::sum));
        map55.forEach((k, v) -> map5.merge(k, v, Double::sum));
        Map<String, Double> map6 = getItem6(list1);
        Map<String, Double> map62 = getItem6(list2);
        Map<String, Double> map63 = getItem6(list3);
        Map<String, Double> map64 = getItem6(list4);
        Map<String, Double> map65 = getItem6(list5);
        map62.forEach((k, v) -> map6.merge(k, v, Double::sum));
        map63.forEach((k, v) -> map6.merge(k, v, Double::sum));
        map64.forEach((k, v) -> map6.merge(k, v, Double::sum));
        map65.forEach((k, v) -> map6.merge(k, v, Double::sum));
        Map<String, Double> map7 = getItem78(list1);
        Map<String, Double> map72 = getItem78(list2);
        Map<String, Double> map73 = getItem78(list3);
        Map<String, Double> map74 = getItem78(list4);
        Map<String, Double> map75 = getItem78(list5);
        map72.forEach((k, v) -> map7.merge(k, v, Double::sum));
        map73.forEach((k, v) -> map7.merge(k, v, Double::sum));
        map74.forEach((k, v) -> map7.merge(k, v, Double::sum));
        map75.forEach((k, v) -> map7.merge(k, v, Double::sum));
        Set<String> badItems = new HashSet<>();
        badItems.add("Charon's Coin");
        badItems.add("Prophetic Cloak");
        badItems.add("Devourer's Gauntlet");
        badItems.add("Gauntlet of Thebes");
        badItems.add("Transcendence");
        badItems.add("Book of Thoth");
        badItems.add("Soul Eater");
        badItems.add("Warlock's Staff");
        badItems.add("Rage");
        badItems.add("Cursed Orb");
        badItems.add("Honed Edge");
        badItems.add("Knight's Shield");
        badItems.add("Apprentice Staff");
        badItems.add("Warrior's Bane");
        badItems.add("Spell Focus");
        badItems.add("Cloak of Concentration");
        badItems.add("Sage's Stone");
        badItems.add("Steel Mail");
        badItems.add("Talon Trinket");
        badItems.add("Heavy Mace");
        badItems.add("Restored Artifact");
        badItems.add("Jeweled Studs");
        badItems.add("Enchanted Spear");
        badItems.add("Emerald Talisman");
        badItems.add("Cursed Gauntlet");
        badItems.add("Sorcerer's Staff");
        badItems.add("Short Sword");
        badItems.add("Rod of Healing");
        badItems.add("Heavy Hammer");
        badItems.add("Fortified Scepter");
        badItems.add("Book of Souls");
        badItems.add("Gleaming Ear Cuffs");
        badItems.add("Enchanted Ring");
        badItems.add("Thousand Fold Blade");
        badItems.add("Spellbound Kusari");
        badItems.add("Silver Talisman");
        badItems.add("Emerald Mail");
        badItems.add("Cursed Blade");
        badItems.add("Balanced Blade");
        badItems.add("Rose Spike Earrings");
        badItems.add("Hydra's Star");
        badItems.add("Hunter's Bow");
        badItems.add("Charged Morningstar");
        badItems.add("Charged Bow");
        badItems.add("Tower Shield");
        badItems.add("Cleric's Cloak");
        badItems.add("Adventurer's Blade");
        badItems.add("Enchanted Trinket");
        badItems.add("Silver Breastplate");
        badItems.add("Bound Gauntlet");
        badItems.add("Nettle Acorn");
        badItems.add("Lively Acorn");
        badItems.add("Teleport Fragment");
        badItems.add("Sundering Spear");
        badItems.add("Shield of Thorns");
        badItems.add("Purification Beads");
        badItems.add("Magic Shell");
        badItems.add("Horrific Emblem");
        badItems.add("Heavenly Wings");
        badItems.add("Cursed Ankh");
        badItems.add("Cloak of Meditation");
        badItems.add("Bracer of Radiance");
        badItems.add("Blink Rune");
        badItems.add("Belt of Frenzy");
        badItems.add("Aegis Amulet");
        badItems.add("Greater Teleport Fragment");
        badItems.add("Greater Sundering Spear");
        badItems.add("Greater Shield of Thorns");
        badItems.add("Greater Purification Beads");
        badItems.add("Greater Magic Shell");
        badItems.add("Greater Horrific Emblem");
        badItems.add("Greater Heavenly Wings");
        badItems.add("Greater Cursed Ankh");
        badItems.add("Greater Cloak of Meditation");
        badItems.add("Greater Bracer of Radiance");
        badItems.add("Greater Blink Rune");
        badItems.add("Greater Belt of Frenzy");
        badItems.add("Greater Aegis Amulet");
        badItems.add("Magic Acorn");
        badItems.add("Mystical Earrings");
        badItems.add("Ancient Blade");
        badItems.add("Lost Artifact");
        badItems.add("Tiny Trinket");
        badItems.add("Breastplate");
        badItems.add("Cloak");
        badItems.add("Emerald Ring");
        badItems.add("Glowing Emerald");
        badItems.add("Katana");
        badItems.add("Light Blade");
        badItems.add("Morningstar");
        badItems.add("Spiked Gauntlet ");
        badItems.add("Cudgel");
        badItems.add("Druid Stone");
        badItems.add("Enchanted Kusari");
        badItems.add("Iron Mail");
        badItems.add("Mace");
        badItems.add("Magic Focus");
        badItems.add("Round Shield");
        badItems.add("Short Bow");
        badItems.add("Spellbook");
        badItems.add("Talisman");
        badItems.add("Uncommon Staff");
        badItems.add("Hidden Dagger");
        badItems.add("Gem of Fate");
        badItems.add("Sentinel's Gift");
        badItems.add("Benevolence");
        badItems.add("Bumba's Dagger");
        badItems.add("Tainted Steel");
        badItems.add("War Flag");
        badItems.add("Death's Toll");
        badItems.add("Eye of the Jungle");
        badItems.add("Fighter's Mask");
        badItems.add("Gilded Arrow");
        badItems.add("Leather Cowl");
        badItems.add("Protector's Mask");
        badItems.add("Vampiric Shroud");
        badItems.add("Warding Sigil");
        badItems.add("Warrior's Axe");
        badItems.add("Bluestone Pendant");
        badItems.add("Conduit Gem");
        badItems.add("Sands of Time");
        badItems.add("Manikin Scepter");
        badItems.add("");
        map1.keySet().removeAll(badItems);
        map2.keySet().removeAll(badItems);
        map3.keySet().removeAll(badItems);
        map4.keySet().removeAll(badItems);
        map5.keySet().removeAll(badItems);
        map6.keySet().removeAll(badItems);
        map7.keySet().removeAll(badItems);
        if (map1.size() > 9) {
            Map<String,Double> topTen =
                    map1.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .limit(10)
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            map1.clear();
            map1.putAll(topTen);
        }
        if (map2.size() > 9) {
            Map<String,Double> topTen =
                    map2.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .limit(10)
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            map2.clear();
            map2.putAll(topTen);
        }
        if (map3.size() > 9) {
            Map<String,Double> topTen =
                    map3.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .limit(10)
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            map3.clear();
            map3.putAll(topTen);
        }
        if (map4.size() > 9) {
            Map<String,Double> topTen =
                    map4.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .limit(10)
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            map4.clear();
            map4.putAll(topTen);
        }
        if (map5.size() > 9) {
            Map<String,Double> topTen =
                    map5.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .limit(10)
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            map5.clear();
            map5.putAll(topTen);
        }
        if (map6.size() > 9) {
            Map<String,Double> topTen =
                    map6.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .limit(10)
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            map6.clear();
            map6.putAll(topTen);
        }
        Map<String,Double> topTen =
                map7.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(10)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        map7.clear();
        map7.putAll(topTen);


        Map <String[], Double> mapTest = new HashMap<>();
        Map <String[], Double> mapSorted = new LinkedHashMap<>();
        long start = System.currentTimeMillis();
        for(Map.Entry<String, Double> entry1 : map1.entrySet()){
            for(Map.Entry<String, Double> entry2 : map2.entrySet()){
                for(Map.Entry<String, Double> entry3 : map3.entrySet()){
                    for(Map.Entry<String, Double> entry4 : map4.entrySet()){
                        for(Map.Entry<String, Double> entry5 : map5.entrySet()){
                            for(Map.Entry<String, Double> entry6 : map6.entrySet()) {
                                double value = entry1.getValue()+entry2.getValue()+entry3.getValue()+entry4.getValue()+entry5.getValue()+entry6.getValue();

                                String[] ss = {entry1.getKey(), entry2.getKey(), entry3.getKey(), entry4.getKey(), entry5.getKey(), entry6.getKey()};
                                mapTest.put(ss, value);
                            }
                        }
                    }
                }
            }
        }
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Прошло времени, мс: " + elapsed);

        String build = "";
        long start1 = System.currentTimeMillis();
        mapTest.entrySet().stream()
                .sorted(Map.Entry.<String[], Double>comparingByValue().reversed())
                .forEach(entry -> mapSorted.put(entry.getKey(), entry.getValue()));
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("Прошло времени, мс: " + elapsed1);
        Iterator<Map.Entry<String[], Double>> entries = mapSorted.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String[], Double> entry = entries.next();
            /* System.out.println(Arrays.toString(entry.getKey()) +"    /      " + entry.getValue());*/
            if (!checkForDuplicates(entry.getKey()) && !checkForGlyphs(entry.getKey()) && !checkForDuplicatesGlyphs(entry.getKey())) {
                build = Arrays.toString(entry.getKey());
                break;
            }
        }

     /*   String first = "";
        String second = "";
        String third = "";
        String fourth = "";
        String fifth = "";
        String sixth = "";
        String seventh = "";*/

        List<String> list11= new ArrayList<>();
        for (Map.Entry<String, Double> entry : map1.entrySet()) {
           /* if (entry.getValue()==0.00) {
                first = entry.getKey();
                continue;
            }*/
            list11.add(entry.getKey()+"/"+Math.round(10000*entry.getValue()));
        }
        List<String> list12= new ArrayList<>();
        for (Map.Entry<String, Double> entry : map2.entrySet()) {
           /* if (entry.getValue()==0.00) {
                second = entry.getKey();
                continue;
            }*/
            list12.add(entry.getKey()+"/"+Math.round(10000*entry.getValue()));
        }
        List<String> list13= new ArrayList<>();
        for (Map.Entry<String, Double> entry : map3.entrySet()) {
          /*  if (entry.getValue()==0.00) {
                third = entry.getKey();
                continue;
            }*/
            list13.add(entry.getKey()+"/"+Math.round(10000*entry.getValue()));
        }
        List<String> list14= new ArrayList<>();
        for (Map.Entry<String, Double> entry : map4.entrySet()) {
          /*  if (entry.getValue()==0.00) {
                fourth = entry.getKey();
                continue;
            }*/
            list14.add(entry.getKey()+"/"+Math.round(10000*entry.getValue()));
        }
        List<String> list15= new ArrayList<>();
        for (Map.Entry<String, Double> entry : map5.entrySet()) {
            /*if (entry.getValue()==0.00) {
                fifth = entry.getKey();
                continue;
            }*/
            list15.add(entry.getKey()+"/"+Math.round(10000*entry.getValue()));
        }
        List<String> list16= new ArrayList<>();
        for (Map.Entry<String, Double> entry : map6.entrySet()) {
          /*  if (entry.getValue()==0.00) {
                sixth = entry.getKey();
                continue;
            }*/
            list16.add(entry.getKey()+"/"+Math.round(10000*entry.getValue()));
        }
        List<String> list17= new ArrayList<>();
        for (Map.Entry<String, Double> entry : map7.entrySet()) {
           /* if (entry.getValue()==0.00) {
                seventh = entry.getKey();
                continue;
            }*/
            list17.add(entry.getKey()+"/"+Math.round(10000*entry.getValue()));
        }
        return new SmiteObject(list11, list12, list13, list14, list15, list16, list17, build);
    }

}
