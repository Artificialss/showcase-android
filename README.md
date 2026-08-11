# Artificialss Showcase — Android

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A small, standalone native Android app demonstrating our mobile engineering: **Kotlin + Jetpack Compose**,
**MVVM with Clean Architecture**, **Koin** for dependency injection, and **Navigation Compose's type-safe routes**.

It recreates the same Hero and Portfolio content shown on [Showcase.NextJS](https://github.com/Artificialss/Showcase.NextJS),
rebuilt for phones with a bottom navigation bar and an auto-advancing portfolio pager — no backend, no credentials.

## What's in here

A splash screen, then two screens behind a bottom navigation bar:

- **Splash** — fades in the brand logo and tagline on the brand-green background, then auto-navigates to Home.
- **Home** — logo with an animated "Powered by AI" sign, headline, subtitle, and a CTA button that opens
  [artificialss.ai](https://artificialss.ai) in the browser.
- **Portfolio** — a starry black-sky background with a `HorizontalPager` that auto-advances every 2 seconds
  through 5 projects (including this repo itself), each with an illustrated Canvas mockup and a tap-through
  link to the real project. A small moon icon sits above the bottom navigation bar.

## Architecture

Clean Architecture in three layers, MVVM on top:

```
domain/              # Pure Kotlin, zero Android dependencies
├── model/           # PortfolioItem
├── repository/      # PortfolioRepository interface
└── usecase/         # GetPortfolioItemsUseCase

data/                # Implementations
├── mock/            # PortfolioMockGenerator — static project list, no backend
└── repository/      # PortfolioRepositoryImpl

ui/                  # Presentation (MVVM)
├── feature/splash/        # SplashScreen — fade-in logo, auto-navigates to Home
├── feature/home/          # HomeViewModel + HomeScreen
├── feature/portfolio/     # PortfolioViewModel + PortfolioScreen + Canvas mockups
├── navigation/            # Type-safe AppRoute, NavHost, bottom nav bar
├── theme/                 # Brand color tokens ported from the Next.js site's design system
└── components/            # Logo, SpaceBackground, MoonBadge, URL launcher helper

di/                  # Koin module — one place wiring repository → use case → ViewModel
```

- **ViewModels have zero Compose imports** — they expose `StateFlow<UiState>`, consumed via
  `collectAsState()`. The pager's own animation timing (the 2-second auto-advance) lives in the Composable,
  since `PagerState` is a Compose-only concept — not business logic.
- **Navigation** uses `androidx.navigation.compose` type-safe routes: `AppRoute` is a `sealed interface` with
  `@Serializable data object` destinations, matched via `composable<AppRoute.Home>` — no string routes.
- **DI** is Koin: `single<PortfolioRepository>`, `factory { UseCase(...) }`, `viewModel { ... }`, all wired in
  one `AppModule.kt`.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin 2.4.0 |
| UI | Jetpack Compose (BOM 2026.06.01) |
| Navigation | Navigation Compose 2.9.8, type-safe `@Serializable` routes |
| DI | Koin 4.2.2 |
| Architecture | MVVM + Clean Architecture (domain / data / presentation) |
| Min SDK / Target SDK | 26 / 36 |

## What's deliberately left out

No environment variables, API keys, analytics, or backend of any kind — the portfolio list is a static,
deterministic mock generator, matching the pattern used in the other Showcase.* repos. All external links open
in the device browser via a plain `Intent`.

## Run it locally

Open in Android Studio and run the `app` module, or from the command line:

```shell
./gradlew assembleDebug
```

Requires a local Android SDK — `local.properties` (git-ignored) must point `sdk.dir` at your SDK install.

## License

MIT — see [LICENSE](LICENSE). Free to use, modify, and distribute.

---

Built with Jetpack Compose by **Artificialss**
