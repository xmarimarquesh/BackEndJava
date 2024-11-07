using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using frontend;

var builder = WebAssemblyHostBuilder.CreateDefault(args);
builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

await builder.Build().RunAsync();

public static class Configs
{
    public static string Backend { get; set; } = "http://localhost:8080/";

    public static HttpClient Client {
        get
        {
            var http = new HttpClient {
                BaseAddress = new Uri(Backend)
            };

            return http;
        }
    }
}