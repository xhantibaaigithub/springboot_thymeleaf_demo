# springboot_thymeleaf_demo

# Grab-a-Grub-Bud

Grab-a-Grub-Bud is a web application for managing restaurants. It allows users to add, edit, delete, and filter
restaurants, and is built using Spring Boot and Thymeleaf.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Installation

### Prerequisites

- Java 11 or higher
- Maven
- An IDE like IntelliJ IDEA or Visual Studio Code

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/xhantibaaigithub/springboot_thymeleaf_demo.git
   cd grab-a-grub-bud

2. Build the project:
   ```bash
   mvn clean install

3. Run the project:
   ```bash
   mvn spring-boot:run

4. Open a web browser and navigate to `http://localhost:8080` to access the application.

## Usage

### Endpoints

- `GET /` : Displays the home page.
- `GET /restaurants` : Displays a list of all restaurants. Supports an optional applyfilter query parameter.
- `GET /restaurants/add` : Displays the form to add a new restaurant.
- `POST /restaurants/add_new` : Adds a new restaurant.
- `GET /restaurants/edit/{id}` : Displays the form to edit an existing restaurant.
- `POST /restaurants/edit` : Edits an existing restaurant.
- `GET /restaurants/delete/{id}` : Displays the confirmation page to delete a restaurant.
- `POST /restaurants/delete/confirmed` : Deletes a restaurant.

### Testing

To run the tests, execute the following command:

   ```bash
   mvn test.
   ```

#### RestaurantService Tests

- filterSearchResults()
- save()
- findById()
- edit()
- delete()

#### RestaurantController Tests

- getRestaurants()
- addRestaurantPage()
- addRestaurant()
- editRestaurantPage()
- editRestaurant()
- deleteRestaurantPage()
- deleteRestaurant()

#### HomeController Tests

- home()

## Contributing

- Xhanti Baai
- Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License.
