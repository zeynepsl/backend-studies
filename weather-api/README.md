# Weather API

Modern hava durumu uygulaması - Spring Boot 3.4.3 ve Java 21 ile geliştirilmiştir.

## Özellikler

- Şehir adına göre hava durumu sorgulama
- Koordinatlara göre hava durumu sorgulama
- Ülke kodu ile filtreleme
- OpenWeatherMap API entegrasyonu
- RESTful API
- Exception handling
- Logging

## Teknolojiler

- Java 21
- Spring Boot 3.4.3
- Spring WebFlux (WebClient)
- Lombok
- Maven

## Kurulum

### 1. API Key Alma

OpenWeatherMap'ten ücretsiz API key alın:
1. [OpenWeatherMap](https://openweathermap.org/api) adresine gidin
2. Ücretsiz hesap oluşturun
3. API Keys bölümünden key'inizi alın

### 2. API Key'i Yapılandırma

API key'inizi environment variable olarak ayarlayın:

```bash
export WEATHER_API_KEY=your_actual_api_key_here
```

Veya `application.properties` dosyasındaki `your_api_key_here` kısmını değiştirin.

### 3. Projeyi Çalıştırma

```bash
cd weather-api
mvn clean install
mvn spring-boot:run
```

Uygulama `http://localhost:8080` adresinde çalışacaktır.

## API Endpoints

### 1. Şehir adına göre hava durumu

```bash
GET /api/v1/weather/current?city=Istanbul
GET /api/v1/weather/current?city=Istanbul&country=TR
```

Örnek:
```bash
curl "http://localhost:8080/api/v1/weather/current?city=Istanbul"
```

### 2. Şehir adı ile (path variable)

```bash
GET /api/v1/weather/current/{city}
```

Örnek:
```bash
curl "http://localhost:8080/api/v1/weather/current/Istanbul"
```

### 3. Koordinatlara göre hava durumu

```bash
GET /api/v1/weather/coordinates?lat=41.0082&lon=28.9784
```

Örnek:
```bash
curl "http://localhost:8080/api/v1/weather/coordinates?lat=41.0082&lon=28.9784"
```

### 4. Health Check

```bash
GET /api/v1/weather/health
```

## Response Örneği

```json
{
  "cityName": "Istanbul",
  "country": "TR",
  "temperature": 15.5,
  "feelsLike": 14.2,
  "tempMin": 13.0,
  "tempMax": 17.0,
  "humidity": 65,
  "pressure": 1013,
  "weatherCondition": "Clouds",
  "weatherDescription": "parçalı bulutlu",
  "windSpeed": 3.5,
  "windDegrees": 180,
  "cloudiness": 75,
  "visibility": 10000,
  "latitude": 41.0082,
  "longitude": 28.9784,
  "timestamp": "2024-03-04T12:00:00Z",
  "sunrise": "2024-03-04T06:30:00Z",
  "sunset": "2024-03-04T18:45:00Z"
}
```

## Yapılandırma

`application.properties` dosyasında aşağıdaki ayarları değiştirebilirsiniz:

- `weather.api.units`: `metric` (Celsius), `imperial` (Fahrenheit), veya `standard` (Kelvin)
- `weather.api.language`: `tr`, `en`, vb. (dil kodu)
- `server.port`: Uygulama portu (varsayılan: 8080)

## Geliştirme

Development profile ile çalıştırmak için:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Test

```bash
mvn test
```

## Lisans

MIT
